package com.ottf.quizdroid.presentation.settings

import com.ottf.quizdroid.presentation.settings.model.UserProfile

data class SettingState(
    val userProfile: UserProfile = UserProfile.EMPTY,
    val isPushNotificationEnabled: Boolean = false,
    val currentVersion: String = "",
    val recentVersion: String = "",
)
