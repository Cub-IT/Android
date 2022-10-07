package ua.university.auth.sign_up

data class SingUpScreenArgs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)
