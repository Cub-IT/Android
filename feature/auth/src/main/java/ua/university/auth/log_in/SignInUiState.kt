package ua.university.auth.log_in

import ua.university.auth.log_in.item.UserSignInItem
import ua.university.ui.mvi.UiState

sealed class SignInUiState(val user: UserSignInItem, val isSignInEnabled: Boolean) : UiState() {

    class WaitingUserData(user: UserSignInItem, isSignInEnabled: Boolean)
        : SignInUiState(user = user, isSignInEnabled = isSignInEnabled)

    class WaitingResponse(user: UserSignInItem)
        : SignInUiState(user = user, isSignInEnabled = false)

    class FailedSignIn(user: UserSignInItem, val cause: String?, isSignInEnabled: Boolean)
        : SignInUiState(user = user, isSignInEnabled = isSignInEnabled)

}