package com.ottf.quizdroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ottf.quizdroid.presentation.home.HomeScreenRoot
import com.ottf.quizdroid.presentation.home.HomeViewModel
import com.ottf.quizdroid.presentation.quiz.QuizScreenRoot
import com.ottf.quizdroid.presentation.quiz.QuizViewModel
import com.ottf.quizdroid.ui.theme.QuizDroidTheme

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
                            val viewModel = HomeViewModel()
                            HomeScreenRoot(
                                viewModel = viewModel,
                                onNavigateToQuiz = {
                                    navController.navigate(Route.Quiz)
                                },
                            )
                        }
                        composable<Route.Quiz> {
                            val viewModel = QuizViewModel()
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
