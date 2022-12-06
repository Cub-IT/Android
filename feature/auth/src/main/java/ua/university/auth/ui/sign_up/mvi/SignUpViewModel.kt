package ua.university.auth.ui.sign_up.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.auth.data.repository.AuthRepository
import ua.university.auth.ui.sign_up.item.SignUpItem
import ua.university.auth.ui.sign_up.screen.SignUpScreenArgs
import ua.university.network.result.onResult
import ua.university.ui.item.InputFiled
import ua.university.ui.item.toUiText
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import javax.inject.Named

class SignUpViewModel @AssistedInject constructor(
    @Assisted private val args: SignUpScreenArgs,
    private val authRepository: AuthRepository,
    @Named("emailLinter") private val emailLinter: InputLinter,
    @Named("passwordLinter") private val passwordLinter: InputLinter,
    @Named("nameLinter") private val nameLinter: InputLinter,
) : BaseViewModel<SignUpEvent, SignUpState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: SignUpScreenArgs): SignUpViewModel
    }

    override fun createInitialState(): SignUpState {
        val user = SignUpItem(
            firstName = InputFiled(""),
            lastName = InputFiled(""),
            email = InputFiled(""),
            password = InputFiled(""),
        )
        return SignUpState.WaitingUserData(
            user = user,
            isSignUpEnabled = isSignUpEnabled(user)
        )
    }

    override fun handleEvent(event: SignUpEvent) {
        when (val currentState = uiState.value) {
            is SignUpState.WaitingResponse -> reduce(event, currentState)
            is SignUpState.WaitingUserData -> reduce(event, currentState)
            is SignUpState.FailedSignUp -> reduce(event, currentState)
        }
    }

    private fun reduce(event: SignUpEvent, currentState: SignUpState.WaitingResponse) {
        when (event) {
            is SignUpEvent.SignUp -> signUp(user = currentState.user)
            is SignUpEvent.NavigateToSignIn -> args.navs.onLogInClicked()
            is SignUpEvent.UpdateUserFirstName -> {}
            is SignUpEvent.UpdateUserLastName -> {}
            is SignUpEvent.UpdateUserEmail -> {}
            is SignUpEvent.UpdateUserPassword -> {}
        }
    }

    private fun reduce(event: SignUpEvent, currentState: SignUpState.WaitingUserData) {
        when (event) {
            is SignUpEvent.SignUp -> signUp(user = currentState.user)
            is SignUpEvent.NavigateToSignIn -> args.navs.onLogInClicked()
            is SignUpEvent.UpdateUserFirstName -> {
                val userInput = SignUpItem(
                    firstName = getNewName(event.firstName),
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserLastName -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = getNewName(event.lastName),
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserEmail -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserPassword -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignUpState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
        }
    }

    private fun reduce(event: SignUpEvent, currentState: SignUpState.FailedSignUp) {
        when (event) {
            is SignUpEvent.SignUp -> signUp(user = currentState.user)
            is SignUpEvent.NavigateToSignIn -> args.navs.onLogInClicked()
            is SignUpEvent.UpdateUserFirstName -> {
                val userInput = SignUpItem(
                    firstName = getNewName(event.firstName),
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserLastName -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = getNewName(event.lastName),
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserEmail -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignUpState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }

            is SignUpEvent.UpdateUserPassword -> {
                val userInput = SignUpItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignUpState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
        }
    }

    private fun isSignUpEnabled(user: SignUpItem): Boolean {
        return (user.firstName.error == null) and
                (user.firstName.value.isNotEmpty()) and

                (user.lastName.error == null) and
                (user.lastName.value.isNotEmpty()) and

                (user.email.error == null) and
                (user.email.value.isNotEmpty()) and

                (user.password.error == null) and
                (user.password.value.isNotEmpty())
    }

    private fun signUp(user: SignUpItem) {
        _uiState.value = SignUpState.WaitingResponse(user = user)
        viewModelScope.launch {
            authRepository.signUp(
                firstName = user.firstName.value,
                lastName = user.lastName.value,
                email = user.email.value,
                password = user.password.value
            ).onResult(
                onSuccess = { args.navs.onSignUpClicked() },
                onFailure = {
                    _uiState.value = SignUpState.FailedSignUp(
                        user = user,
                        cause = it.cause.message.toUiText(),//.readableCause(),
                        isSignUpEnabled(user)
                    )
                }
            )
        }
    }

    private fun getNewName(name: String): InputFiled {
        return InputFiled(
            value = name,
            error = nameLinter.check(name.trim())
        )
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