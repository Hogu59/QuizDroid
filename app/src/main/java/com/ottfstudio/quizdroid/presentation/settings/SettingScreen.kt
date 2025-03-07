@file:OptIn(ExperimentalMaterial3Api::class)

package com.ottfstudio.quizdroid.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ottfstudio.quizdroid.BuildConfig
import com.ottfstudio.quizdroid.presentation.settings.components.SettingCard
import com.ottfstudio.quizdroid.presentation.settings.components.SettingInfoItem
import com.ottfstudio.quizdroid.presentation.settings.components.SettingListItem
import com.ottfstudio.quizdroid.ui.theme.Gray100

@Composable
fun SettingsScreen(
    // state: SettingState,
    onBackPress: () -> Unit = {},
    onClickOss: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var pushNotificationsEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("설정", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                ),
                modifier = Modifier.shadow(1.dp),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Gray100)
                .padding(16.dp),
        ) {
            // 프로필 카드
            // ProfileCard(state.userProfile)

            // 푸시 알림 스위치
//            SettingCard(
//                title = "앱 설정",
//                content = {
//                    SettingSwitchItem(
//                        title = "푸시 알림",
//                        checked = pushNotificationsEnabled,
//                        onCheckedChange = { pushNotificationsEnabled = it },
//                    )
//                },
//            )

            SettingCard(
                title = "약관 및 정책",
                content = {
                    Column {
//                        SettingListItem(
//                            title = "개인정보처리방침",
//                            onClick = { TODO() },
//                        )
//                        HorizontalDivider(thickness = 0.5.dp)
//                        SettingListItem(
//                            title = "이용약관",
//                            onClick = { TODO() },
//                        )
//                        HorizontalDivider(
//                            thickness = 0.5.dp,
//                        )
                        SettingListItem(
                            title = "오픈소스 라이선스",
                            onClick = {
                                onClickOss()
                            },
                        )
                    }
                },
            )

            SettingCard(
                title = "앱 정보",
                content = {
                    Column {
                        SettingInfoItem(title = "현재 버전", value = BuildConfig.VERSION_NAME)
                        HorizontalDivider(thickness = 0.5.dp)
                        SettingInfoItem(title = "최신 업데이트", value = "2025.02.27")
                    }
                },
            )

//            Button(
//                onClick = { /* TODO: 로그아웃 액션 */ },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                border = BorderStroke(1.dp, Color.Red),
//                shape = RoundedCornerShape(4.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 24.dp),
//            ) {
//                Text(
//                    "로그아웃",
//                    fontSize = 14.sp,
//                    color = Color.Red,
//                    modifier = Modifier.padding(vertical = 6.dp),
//                )
//            }
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(
//        state = SettingState(
//            userProfile = UserProfile(
//                name = "김민준",
//                email = "minjun.kim@example.com",
//                profileImage = "",
//            ),
//        ),
    )
}
