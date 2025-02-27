package com.ottfstudio.quizdroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ottfstudio.quizdroid.presentation.home.HomeScreenRoot
import com.ottfstudio.quizdroid.presentation.home.HomeViewModel
import com.ottfstudio.quizdroid.presentation.quiz.QuizScreenRoot
import com.ottfstudio.quizdroid.presentation.quiz.QuizViewModel
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
                NavHost(
                    navController = navController,
                    startDestination = Route.QuizDroidGraph,
                ) {
                    navigation<Route.QuizDroidGraph>(
                        startDestination = Route.Home,
                    ) {
                        composable<Route.Home> {
                            val viewModel: HomeViewModel = hiltViewModel()
                            HomeScreenRoot(
                                viewModel = viewModel,
                                onNavigateToQuiz = {
                                    navController.navigate(Route.Quiz)
                                },
                            )
                        }
                        composable<Route.Quiz> {
                            val viewModel: QuizViewModel = hiltViewModel()
                            QuizScreenRoot(
                                viewModel = viewModel,
                                onNavigateBack = {
                                    navController.navigateUp()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
