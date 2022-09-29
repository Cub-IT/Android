package ua.university.auth.log_in

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.auth.log_in.item.UserSignInItem
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import ua.university.user.UserRepository
import ua.university.user.model.LogInUser
import ua.university.util.result.onResult
import javax.inject.Named

class SignInViewModel @AssistedInject constructor(
    @Assisted("signIn") private val onSignInClicked: () -> Unit,
    @Assisted("signUp") private val onSignUpClicked: () -> Unit,
    private val userRepository: UserRepository,
    @Named("emailLinter") private val emailLinter: InputLinter,
    @Named("passwordLinter") private val passwordLinter: InputLinter
) : BaseViewModel<SignInUiEvent, SignInUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("signIn") onSignInClicked: () -> Unit,
            @Assisted("signUp") onSignUpClicked: () -> Unit
        ): SignInViewModel
    }

    override fun createInitialState(): SignInUiState {
        val user = UserSignInItem(email = InputFiled(""), password = InputFiled(""))
        return SignInUiState.WaitingUserData(
            user = user,
            isSignInEnabled = isSignInEnabled(user)
        )
    }

    override fun handleEvent(event: SignInUiEvent) {
        when (val currentState = _uiState.value) {
            is SignInUiState.FailedSignIn -> reduce(event, currentState)
            is SignInUiState.WaitingResponse -> reduce(event, currentState)
            is SignInUiState.WaitingUserData -> reduce(event, currentState)
        }
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.FailedSignIn) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.NavigateToSignUp -> onSignUpClicked()
            is SignInUiEvent.UpdateUserEmail -> {
                val userInput = UserSignInItem(
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignInUiState.FailedSignIn(
                    user = userInput,
                    cause = currentState.cause,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
            is SignInUiEvent.UpdateUserPassword -> {
                val userInput = UserSignInItem(
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignInUiState.FailedSignIn(
                    user = userInput,
                    cause = currentState.cause,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
        }
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingResponse) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.NavigateToSignUp -> onSignUpClicked()
            is SignInUiEvent.UpdateUserEmail -> { }
            is SignInUiEvent.UpdateUserPassword -> { }
        }
    }

    private fun reduce(event: SignInUiEvent, currentState: SignInUiState.WaitingUserData) {
        when (event) {
            is SignInUiEvent.SignIn -> signIn(user = currentState.user)
            is SignInUiEvent.NavigateToSignUp -> onSignUpClicked()
            is SignInUiEvent.UpdateUserEmail -> {
                val userInput = UserSignInItem(
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignInUiState.WaitingUserData(
                    user = userInput,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
            is SignInUiEvent.UpdateUserPassword -> {
                val userInput = UserSignInItem(
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignInUiState.WaitingUserData(
                    user = userInput,
                    isSignInEnabled = isSignInEnabled(userInput)
                )
            }
        }
    }

    private fun isSignInEnabled(user: UserSignInItem): Boolean {
        return (user.email.error == null) and
                (user.email.value.isNotEmpty()) and

                (user.password.error == null) and
                (user.password.value.isNotEmpty())
    }

    private fun signIn(user: UserSignInItem) {
        _uiState.value = SignInUiState.WaitingResponse(user = user)
        viewModelScope.launch {
            val logInUser = LogInUser(
                email = user.email.value.trim(),
                password = user.password.value.trim()
            )
            userRepository.logIn(user = logInUser).onResult(
                onSuccess = { onSignInClicked() },
                onFailure = {
                    _uiState.value = SignInUiState.FailedSignIn(
                        user = user,
                        cause = it.cause.message,
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