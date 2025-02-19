package com.ottf.quizdroid.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times

@Composable
fun StudyScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(paddingValues),
        ) {
            // 오늘의 학습 섹션
            StudyProgressSection()

            Spacer(modifier = Modifier.height(16.dp))

            // 오늘의 문제 섹션
            TodayQuestionSection()

            Spacer(modifier = Modifier.height(16.dp))

            // 학습 통계 섹션
            StudyStatisticsSection()
        }
    }
}

@Composable
fun StudyProgressSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("오늘의 학습", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(100.dp),
                ) {
                    CircularProgressIndicator(
                        progress = 3 / 7f,
                        color = Color.Blue,
                        strokeWidth = 8.dp,
                        modifier = Modifier.size(80.dp),
                    )
                    Text("3/7\n문제", color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text("✅ 완료", color = Color.Green, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun TodayQuestionSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("오늘의 문제", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("코루틴 스코프와 컨텍스트", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Yellow, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ) {
                    Text("중급", fontSize = 12.sp, color = Color.Black)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("코루틴의 스코프와 컨텍스트의 차이점을 설명하고, 각 사용 사례를 제시하세요.", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* 문제 풀기 액션 */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            ) {
                Text("문제 풀기", color = Color.White)
            }
        }
    }
}

@Composable
fun StudyStatisticsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("학습 통계", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    Text("42", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("총 문제", fontSize = 14.sp)
                }
                Column {
                    Text("7", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("연속 학습", fontSize = 14.sp)
                }
                Column {
                    Text("85%", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("정답률", fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            StudyStatisticsGraph()
        }
    }
}

@Composable
fun StudyStatisticsGraph() {
    val stats = listOf(4, 2, 6, 5, 2, 7) // 요일별 문제 풀이 개수

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {
        stats.forEachIndexed { index, value ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .height(value * 10.dp)
                        .width(20.dp)
                        .background(Color.Blue, shape = RoundedCornerShape(4.dp)),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(listOf("월", "화", "수", "목", "금", "토", "일")[index], fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White,
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "홈") },
            label = { Text("홈") },
            selected = true,
            onClick = { /* 홈 화면 이동 */ },
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "문제") },
            label = { Text("문제") },
            selected = false,
            onClick = { /* 문제 화면 이동 */ },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Face, contentDescription = "통계") },
            label = { Text("통계") },
            selected = false,
            onClick = { /* 통계 화면 이동 */ },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "설정") },
            label = { Text("설정") },
            selected = false,
            onClick = { /* 설정 화면 이동 */ },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudyScreen() {
    StudyScreen()
}
