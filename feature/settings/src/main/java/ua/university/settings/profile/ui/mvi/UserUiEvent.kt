package ua.university.settings.profile.ui.mvi

import ua.university.ui.mvi.UiEvent

sealed class UserUiEvent : UiEvent {

    object UpdateUserData : UserUiEvent()

    object BackClicked : UserUiEvent()

    object LogoutClicked : UserUiEvent()

}