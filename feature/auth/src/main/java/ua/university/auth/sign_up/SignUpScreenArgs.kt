package ua.university.auth.sign_up

data class SignUpScreenArgs(
    val navs: SignUpScreenNavs,
)

data class SignUpScreenNavs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)