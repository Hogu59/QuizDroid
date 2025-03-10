package com.ottfstudio.quizdroid.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottfstudio.quizdroid.R
import com.ottfstudio.quizdroid.ui.theme.Gray400
import com.ottfstudio.quizdroid.ui.theme.RobotoFontFamily

@Composable
fun TodayStatus(
    date: String,
    isSolved: Boolean,
//    progress: Float,
//    totalQuestions: Int,
//    completedQuestions: Int,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black, RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.todays_learning),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Bold,
                )

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings),
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onNavigateToSettings() },
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = date,
                            color = Gray400,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                        )
                        SolvedMark(isSolved = isSolved)
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    ) {
                        CircularProgressWithText(
                            // progress = progress,
                            // total = totalQuestions,
                            // current = completedQuestions,
                            progress = 3 / 7f,
                            total = 7,
                            current = 3,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TodayStatusPreview() {
    TodayStatus(
        date = "2025년 3월 10일",
        isSolved = false,
        onNavigateToSettings = {},
    )
}
