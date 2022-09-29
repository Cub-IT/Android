package ua.university.auth.sign_up

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ua.university.auth.sign_up.item.UserRegistrationItem
import ua.university.ui.item.InputFiled
import ua.university.ui.mvi.BaseViewModel
import ua.university.ui.util.InputLinter
import ua.university.user.UserRepository
import ua.university.user.model.SignUpUser
import ua.university.util.result.onResult
import javax.inject.Named

class SignUpViewModel @AssistedInject constructor(
    @Assisted("signIn") private val onSignInClicked: () -> Unit,
    @Assisted("signUp") private val onSignUpClicked: () -> Unit,
    private val userRepository: UserRepository,
    @Named("emailLinter") private val emailLinter: InputLinter,
    @Named("passwordLinter") private val passwordLinter: InputLinter,
    @Named("nameLinter") private val nameLinter: InputLinter
) : BaseViewModel<SignUpUiEvent, SignUpUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("signIn") onSignInClicked: () -> Unit,
            @Assisted("signUp") onSignUpClicked: () -> Unit
        ): SignUpViewModel
    }

    override fun createInitialState(): SignUpUiState {
        val user = UserRegistrationItem(
            firstName = InputFiled(""),
            lastName = InputFiled(""),
            email = InputFiled(""),
            password = InputFiled(""),
        )
        return SignUpUiState.WaitingUserData(
            user = user,
            isSignUpEnabled = isSignUpEnabled(user)
        )
    }

    override fun handleEvent(event: SignUpUiEvent) {
        when (val currentState = uiState.value) {
            is SignUpUiState.WaitingResponse -> reduce(event, currentState)
            is SignUpUiState.WaitingUserData -> reduce(event, currentState)
            is SignUpUiState.FailedSignUp -> reduce(event, currentState)
        }
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.WaitingResponse) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.NavigateToSignIn -> onSignInClicked()
            is SignUpUiEvent.UpdateUserFirstName -> { }
            is SignUpUiEvent.UpdateUserLastName -> { }
            is SignUpUiEvent.UpdateUserEmail ->  { }
            is SignUpUiEvent.UpdateUserPassword -> { }
        }
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.WaitingUserData) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.NavigateToSignIn -> onSignInClicked()
            is SignUpUiEvent.UpdateUserFirstName -> {
                val userInput = UserRegistrationItem(
                    firstName = getNewName(event.firstName),
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserLastName -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = getNewName(event.lastName),
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserEmail -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserPassword -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignUpUiState.WaitingUserData(
                    user = userInput,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
        }
    }

    private fun reduce(event: SignUpUiEvent, currentState: SignUpUiState.FailedSignUp) {
        when (event) {
            is SignUpUiEvent.SignUp -> signUp(user = currentState.user)
            is SignUpUiEvent.NavigateToSignIn -> onSignInClicked()
            is SignUpUiEvent.UpdateUserFirstName -> {
                val userInput = UserRegistrationItem(
                    firstName = getNewName(event.firstName),
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserLastName -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = getNewName(event.lastName),
                    email = currentState.user.email,
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserEmail -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = getNewEmail(event.email),
                    password = currentState.user.password
                )
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
            is SignUpUiEvent.UpdateUserPassword -> {
                val userInput = UserRegistrationItem(
                    firstName = currentState.user.firstName,
                    lastName = currentState.user.lastName,
                    email = currentState.user.email,
                    password = getNewPassword(event.password)
                )
                _uiState.value = SignUpUiState.FailedSignUp(
                    user = userInput,
                    cause = currentState.cause,
                    isSignUpEnabled = isSignUpEnabled(userInput)
                )
            }
        }
    }

    private fun isSignUpEnabled(user: UserRegistrationItem): Boolean {
        return (user.firstName.error == null) and
                (user.firstName.value.isNotEmpty()) and

                (user.lastName.error == null) and
                (user.lastName.value.isNotEmpty()) and

                (user.email.error == null) and
                (user.email.value.isNotEmpty()) and

                (user.password.error == null) and
                (user.password.value.isNotEmpty())
    }

    private fun signUp(user: UserRegistrationItem) {
        _uiState.value = SignUpUiState.WaitingResponse(user = user)
        viewModelScope.launch {
            val signUpUser = SignUpUser(
                firstName = user.firstName.value,
                lastName = user.lastName.value,
                email = user.email.value,
                password = user.password.value
            )
            userRepository.signUp(user = signUpUser)
                .onResult(
                    onSuccess = { onSignUpClicked() },
                    onFailure = {
                        _uiState.value = SignUpUiState.FailedSignUp(
                            user = user,
                            cause = it.cause.message,
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