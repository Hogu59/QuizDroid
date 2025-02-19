package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudyStatisticsCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            // 타이틀
            Text(
                text = "학습 통계",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 통계 정보 (총 문제, 연속 학습, 정답률)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatisticsItem(title = "총 문제", value = "42")
                StatisticsItem(title = "연속 학습", value = "7")
                StatisticsItem(title = "정답률", value = "85%", isPercentage = true)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 막대 그래프
            StudyBarChart(data = listOf(5, 2, 7, 4, 3))
        }
    }
}

@Composable
fun StatisticsItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isPercentage: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.width(93.dp).height(52.dp),
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray,
        )
    }
}

@Composable
fun StudyBarChart(data: List<Int>, modifier: Modifier = Modifier) {
    val days = listOf("월", "화", "수", "목", "금")
    val maxHeight = 100.dp // 최대 높이 (비율 조정)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
    ) {
        data.forEachIndexed { index, value ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .height((value * 10).dp)
                        .width(20.dp)
                        .background(Color.Blue, shape = RoundedCornerShape(4.dp)),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = days[index], fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudyStatisticsCard() {
    StudyStatisticsCard()
}
