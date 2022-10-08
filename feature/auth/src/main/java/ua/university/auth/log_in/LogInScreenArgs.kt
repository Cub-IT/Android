package ua.university.auth.log_in

data class LogInScreenArgs(
    val navs: LogInScreenNavs,
)

data class LogInScreenNavs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)