package com.ottfstudio.quizdroid.presentation.home.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottfstudio.quizdroid.R
import com.ottfstudio.quizdroid.ui.theme.Gray200
import com.ottfstudio.quizdroid.ui.theme.Gray400

@Composable
fun StudyStatisticsCard(
    totalCount: Int,
    consecutiveCount: Int,
    correctRate: Int,
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
                text = stringResource(R.string.study_statistics),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 통계 정보 (총 문제, 연속 학습, 정답률)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatisticsItem(title = stringResource(R.string.total_problems), value = totalCount.toString())
                StatisticsItem(title = stringResource(R.string.consecutive_days), value = consecutiveCount.toString())
                StatisticsItem(title = stringResource(R.string.accuracy_rate), value = correctRate.toString(), isPercentage = true)
            }

            // Spacer(modifier = Modifier.height(16.dp))

            // 막대 그래프
            // StudyBarChart(data = listOf(5, 2, 7, 4, 3))
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
    Box(
        modifier = modifier
            .background(Gray200, shape = RoundedCornerShape(8.dp))
            .width(100.dp)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .width(93.dp)
                .height(52.dp),
        ) {
            Text(
                text = value + if (isPercentage) "%" else "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = title,
                fontSize = 14.sp,
                color = Gray400,
            )
        }
    }
}

@Composable
fun StudyBarChart(data: List<Int>, modifier: Modifier = Modifier) {
    val days = listOf(
        stringResource(R.string.monday),
        stringResource(R.string.tuesday),
        stringResource(R.string.wednesday),
        stringResource(R.string.thursday),
        stringResource(R.string.friday),
    )

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
    StudyStatisticsCard(
        totalCount = 42,
        consecutiveCount = 7,
        correctRate = 85,
    )
}
