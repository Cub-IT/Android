package ua.university.auth.ui.log_in.screen

data class LogInScreenArgs(
    val navs: LogInScreenNavs,
)

data class LogInScreenNavs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)