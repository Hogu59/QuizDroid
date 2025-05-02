package com.ottfstudio.quizdroid

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.ottfstudio.quizdroid.notification.AlarmScheduler
import com.ottfstudio.quizdroid.presentation.home.HomeScreenRoot
import com.ottfstudio.quizdroid.presentation.home.HomeViewModel
import com.ottfstudio.quizdroid.presentation.quiz.QuizScreenRoot
import com.ottfstudio.quizdroid.presentation.quiz.QuizViewModel
import com.ottfstudio.quizdroid.presentation.settings.SettingsScreen
import com.ottfstudio.quizdroid.ui.theme.QuizDroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val alarmScheduler = AlarmScheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkNotificationPermissionAndSetupAlarm()
        setupComposeUI()
    }

    private fun checkNotificationPermissionAndSetupAlarm() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || isNotificationPermissionGranted()) {
            setupDailyAlarm()
            return
        }
        requestNotificationPermission()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun isNotificationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            NOTIFICATION_PERMISSION_REQUEST_CODE,
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (isNotificationPermissionResult(requestCode, grantResults)) {
            setupDailyAlarm()
        }
    }

    private fun isNotificationPermissionResult(
        requestCode: Int,
        grantResults: IntArray,
    ): Boolean {
        return requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    private fun setupDailyAlarm() {
        val isExact = alarmScheduler.scheduleDailyAlarm(this)
        if (!isExact) {
            Toast.makeText(this, getString(R.string.toast_warning_notification), Toast.LENGTH_LONG).show()
            alarmScheduler.getExactAlarmSettingsIntent()?.let { startActivity(it) }
        }
    }

    private fun setupComposeUI() {
        setContent {
            QuizDroidTheme {
                val navController = rememberNavController()
                val quizViewModel = hiltViewModel<QuizViewModel>()

                NavHost(
                    navController = navController,
                    startDestination = Route.QuizDroidGraph,
                ) {
                    navigation<Route.QuizDroidGraph>(
                        startDestination = Route.Home,
                    ) {
                        composable<Route.Home> {
                            val homeViewModel: HomeViewModel = hiltViewModel()
                            HomeScreenRoot(
                                viewModel = homeViewModel,
                                quizViewModel = quizViewModel,
                                onNavigateToQuiz = { navController.navigate(Route.Quiz) },
                                onNavigateToSettings = { navController.navigate(Route.Settings) },
                            )
                        }

                        composable<Route.Quiz> {
                            QuizScreenRoot(
                                viewModel = quizViewModel,
                                onNavigateBack = { navController.navigateUp() },
                            )
                        }

                        composable<Route.Settings> {
                            SettingsScreen(
                                onBackPress = { navController.navigateUp() },
                                onClickOss = { navigateToOssLicenses() },
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateToOssLicenses() {
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
    }

    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001
    }
}
