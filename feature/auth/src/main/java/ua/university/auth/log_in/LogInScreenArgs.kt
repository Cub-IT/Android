package ua.university.auth.log_in

data class LogInScreenArgs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)