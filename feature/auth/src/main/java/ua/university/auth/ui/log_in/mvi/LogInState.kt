package ua.university.auth.ui.log_in.mvi

import ua.university.auth.ui.log_in.item.LogInItem
import ua.university.ui.item.Reloadable
import ua.university.ui.item.UiText
import ua.university.ui.mvi.UiState

sealed class LogInState(val user: LogInItem, val isSignInEnabled: Boolean) : UiState {

    class WaitingUserData(user: LogInItem, isSignInEnabled: Boolean) :
        LogInState(user = user, isSignInEnabled = isSignInEnabled)

    class WaitingResponse(user: LogInItem) :
        LogInState(user = user, isSignInEnabled = false)

    class FailedSignIn(user: LogInItem, val cause: UiText?, isSignInEnabled: Boolean) :
        LogInState(user = user, isSignInEnabled = isSignInEnabled)

}