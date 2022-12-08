package ua.university.settings.profile.ui.mvi

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.university.preferences.UserSharedPreferences
import ua.university.settings.profile.data.repository.SettingsRepository
import ua.university.settings.profile.ui.item.UserItem
import ua.university.settings.profile.ui.item.toUserItem
import ua.university.settings.profile.ui.screen.ProfileScreenArgs
import ua.university.ui.mvi.BaseViewModel

class UserViewModel @AssistedInject constructor(
    @Assisted private val args: ProfileScreenArgs,
    private val settingsRepository: SettingsRepository
) : BaseViewModel<UserUiEvent, UserUiState>() {

    @AssistedFactory
    interface Factory {
        fun create(args: ProfileScreenArgs): UserViewModel
    }

    override fun createInitialState(): UserUiState {
        viewModelScope.launch {
            updateUserData()
        }

        return UserUiState.UpdatingUserItem(
            userItem = UserItem(
                firstName = "First name",
                lastName = "Last name",
                email = "Email"
            )
        )
    }

    override fun handleEvent(event: UserUiEvent) {
        when (val currentState = _uiState.value) {
            is UserUiState.UpdatingUserItem -> reduce(event, currentState)
            is UserUiState.UserItemFetched -> reduce(event, currentState)
        }
    }

    private fun reduce(event: UserUiEvent, currentState: UserUiState.UpdatingUserItem) {
        when (event) {
            UserUiEvent.BackClicked -> args.navs.onBackClicked()
            UserUiEvent.UpdateUserData -> { /* nothing to do */ }
            UserUiEvent.LogoutClicked -> viewModelScope.launch {
                settingsRepository.logout()
                args.navs.onLogoutClicked()
            }

        }
    }

    private fun reduce(event: UserUiEvent, currentState: UserUiState.UserItemFetched) {
        when (event) {
            UserUiEvent.BackClicked -> args.navs.onBackClicked()
            UserUiEvent.UpdateUserData -> updateUserData()
            UserUiEvent.LogoutClicked -> viewModelScope.launch {
                settingsRepository.logout()
                args.navs.onLogoutClicked()
            }
        }
    }

    private fun updateUserData() {
        viewModelScope.launch {
            delay(10)
            _uiState.value = UserUiState.UserItemFetched(
                userItem = settingsRepository.getUser().toUserItem()
            )
            _uiState.value = UserUiState.UpdatingUserItem(userItem = uiState.value.userItem)
            // TODO: getting new user data
            delay(2000)
            _uiState.value = UserUiState.UserItemFetched(
                userItem = _uiState.value.userItem
            )
        }
    }

}