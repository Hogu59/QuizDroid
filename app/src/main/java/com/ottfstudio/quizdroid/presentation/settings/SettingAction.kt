package com.ottfstudio.quizdroid.presentation.settings

sealed interface SettingAction {
    data object OnNavigateBack : SettingAction

    data class OnPushNotification(val settingOption: Boolean) : SettingAction

    data object OnOpenSourceLicense : SettingAction

    data object OnTermsOfUse : SettingAction

    data object OnPrivacyPolicy : SettingAction

    data object OnLogout : SettingAction
}
