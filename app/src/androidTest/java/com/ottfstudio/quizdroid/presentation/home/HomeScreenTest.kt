package com.ottfstudio.quizdroid.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ottfstudio.quizdroid.presentation.quiz.QuizState
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 데이터를_로딩하는_상태에서는_문제풀기_버튼을_클릭해도_화면이_변경되지_않는다() {
        // Given
        val homeState = HomeState()
        val quizState = QuizState(isLoading = true)
        var action: HomeAction? = null

        // When
        composeTestRule.setContent {
            HomeScreen(
                state = homeState,
                quizState = quizState,
                onAction = {
                    action = it
                },
            )
        }

        // Then
        composeTestRule.onNodeWithText("문제 풀기").assertIsDisplayed()
        composeTestRule.onNodeWithText("문제 풀기").performClick()
        assertNull(action)
    }

    @Test
    fun 데이터를_로딩이_완료된_상태에서는_문제풀기_버튼을_클릭하면_화면이동_이벤트가_발생한다() {
        // Given
        val homeState = HomeState()
        val quizState = QuizState(isLoading = false)
        var action: HomeAction? = null

        // When
        composeTestRule.setContent {
            HomeScreen(
                state = homeState,
                quizState = quizState,
                onAction = {
                    action = it
                },
            )
        }

        // Then
        composeTestRule.onNodeWithText("문제 풀기").assertIsDisplayed()
        composeTestRule.onNodeWithText("문제 풀기").performClick()
        assertNotNull(action)
        assertEquals(HomeAction.OnNavigateToQuiz, action)
    }
}
