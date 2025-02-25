package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottf.quizdroid.R
import com.ottf.quizdroid.domain.model.Quiz
import com.ottf.quizdroid.ui.theme.CustomTypography

@Composable
fun QuestionCard(
    quiz: Quiz?,
    onChallenge: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.daily_question),
                color = Color.Black,
                style = CustomTypography.titleMedium,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    ) {
                        Text(
                            text = "코루틴 스코프와 컨텍스트",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.weight(1f),
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(Color(0xFFFFF4CC), RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Text(
                                text = "중급",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6D5D00),
                            )
                        }
                    }

                    Text(
                        text = "코루틴의 스코프와 컨텍스트의 차이점을 설명하고, 각자의 사용 사례를 제시하시오.",
                        fontSize = 14.sp,
                        color = Color(0xFF6C757D),
                        modifier = Modifier.padding(top = 8.dp),
                    )

                    Button(
                        onClick = { onChallenge() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text = "문제 풀기",
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuestionCardPreview() {
    QuestionCard(
        quiz = null,
        onChallenge = { /* TODO: 문제 풀기 액션 */ },
    )
}
