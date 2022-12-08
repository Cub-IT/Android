package ua.university.auth.ui.sign_up.screen

data class SignUpScreenArgs(
    val navs: SignUpScreenNavs,
)

data class SignUpScreenNavs(
    val onLogInClicked: () -> Unit,
    val onSignUpClicked: () -> Unit,
)