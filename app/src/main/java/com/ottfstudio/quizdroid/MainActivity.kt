package com.ottfstudio.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.ottfstudio.quizdroid.presentation.home.HomeScreenRoot
import com.ottfstudio.quizdroid.presentation.home.HomeViewModel
import com.ottfstudio.quizdroid.presentation.quiz.QuizScreenRoot
import com.ottfstudio.quizdroid.presentation.quiz.QuizViewModel
import com.ottfstudio.quizdroid.presentation.settings.SettingsScreen
import com.ottfstudio.quizdroid.ui.theme.QuizDroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                                onBackPress = {
                                    navController.navigateUp()
                                },
                                onClickOss = {
                                    startActivity(
                                        Intent(this@MainActivity, OssLicensesMenuActivity::class.java),
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
