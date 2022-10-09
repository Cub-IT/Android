package ua.university.settings.profile.screen

data class ProfileScreenArgs(
    val navs: ProfileScreenNavs
)

data class ProfileScreenNavs(
    val onBackClicked: () -> Unit,
    val onLogoutClicked: () -> Unit,
)