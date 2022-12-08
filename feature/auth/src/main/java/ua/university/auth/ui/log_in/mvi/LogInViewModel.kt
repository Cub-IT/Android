package ua.university.auth.ui.log_in.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.auth.data.repository.AuthRepository
import ua.university.auth.ui.log_in.item.LogInItem
import ua.university.auth.ui.log_in.screen.LogInScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.item.InputFiled
import ua.university.ui.item.toUiText
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class LogInViewModel @AssistedInject constructor(
    @Assisted private val args: LogInScreenArgs,
    private val authRepository: AuthRepository,
    @Named("emailLinter") private val emailLinter: InputLinter,
    @Named("passwordLinter") private val passwordLinter: InputLinter
) : BaseViewModel<LogInEvent, LogInState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: LogInScreenArgs): LogInViewModel
    }

    override fun createInitialState(): LogInState {
        val user = LogInItem(email = InputFiled(""), password = InputFiled(""))
        return LogInState.WaitingUserData(
            user = user,
            isSignInEnabled = isSignInEnabled(user)
        )
    }

    override fun handleEvent(event: LogInEvent) {
        when (val currentState = _uiState.value) {
            is LogInState.FailedSignIn -> reduce(event, currentState)
            is LogInState.WaitingResponse -> reduce(event, currentState)
            is LogInState.WaitingUserData -> reduce(event, currentState)
        }
    }

    private fun reduce(event: LogInEvent, currentState: LogInState.FailedSignIn) {
        when (event) {
            is LogInEvent.SignIn -> signIn(user = currentState.user)
            is LogInEvent.NavigateToSignUp -> args.navs.onSignUpClicked()
            is LogInEvent.UpdateUserEmail -> {
                val userInput = LogInItem(
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = LogInState.FailedSignIn(
                    user = userInput,
                    cause = currentState.cause,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
            is LogInEvent.UpdateUserPassword -> {
                val userInput = LogInItem(
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = LogInState.FailedSignIn(
                    user = userInput,
                    cause = currentState.cause,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
        }
    }

    private fun reduce(event: LogInEvent, currentState: LogInState.WaitingResponse) {
        when (event) {
            is LogInEvent.SignIn -> signIn(user = currentState.user)
            is LogInEvent.NavigateToSignUp -> args.navs.onSignUpClicked()
            is LogInEvent.UpdateUserEmail -> { }
            is LogInEvent.UpdateUserPassword -> { }
        }
    }

    private fun reduce(event: LogInEvent, currentState: LogInState.WaitingUserData) {
        when (event) {
            is LogInEvent.SignIn -> signIn(user = currentState.user)
            is LogInEvent.NavigateToSignUp -> args.navs.onSignUpClicked()
            is LogInEvent.UpdateUserEmail -> {
                val userInput = LogInItem(
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = LogInState.WaitingUserData(
                    user = userInput,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
            is LogInEvent.UpdateUserPassword -> {
                val userInput = LogInItem(
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = LogInState.WaitingUserData(
                    user = userInput,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
        }
    }

    private fun isSignInEnabled(user: LogInItem): Boolean {
        return (user.email.error == null) and
                (user.email.value.isNotEmpty()) and

                (user.password.error == null) and
                (user.password.value.isNotEmpty())
    }

    private fun signIn(user: LogInItem) {
        _uiState.value = LogInState.WaitingResponse(user = user)
        viewModelScope.launch {
            authRepository.signIn(
                email = user.email.value.trim(),
                password = user.password.value.trim()
            ).onResult(
                onSuccess = { args.navs.onLogInClicked() },
                onFailure = {
                    _uiState.value = LogInState.FailedSignIn(
                        user = user,
                        cause = it.cause.message.toUiText(),//it.error.readableCause(),
                        isSignInEnabled = isSignInEnabled(user)
                    )
                }
            )

        }
    }

    private fun getNewEmail(email: String): InputFiled {
        return InputFiled(
            value = email,
            error = emailLinter.check(email.trim())
        )
    }

    private fun getNewPassword(password: String): InputFiled {
        return InputFiled(
            value = password,
            error = passwordLinter.check(password.trim())
        )
    }
}