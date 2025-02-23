package com.ottf.quizdroid.presentation.settings.model

data class UserProfile(
    val name: String,
    val email: String,
    val profileImage: String,
) {
    companion object {
        val EMPTY = UserProfile(
            name = "",
            email = "",
            profileImage = "",
        )
    }
}
