package com.ottfstudio.quizdroid.presentation.settings

import com.ottfstudio.quizdroid.presentation.settings.model.UserProfile

data class SettingState(
    val userProfile: UserProfile = UserProfile.EMPTY,
    val isPushNotificationEnabled: Boolean = false,
    val currentVersion: String = "",
    val recentVersion: String = "",
)
