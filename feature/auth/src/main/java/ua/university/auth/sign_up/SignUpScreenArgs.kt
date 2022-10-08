package ua.university.auth.sign_up

data class SignUpScreenArgs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)
