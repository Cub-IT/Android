package ua.university.settings.profile.ui.mvi

import ua.university.settings.profile.ui.item.UserItem
import ua.university.ui.mvi.UiState

sealed class UserUiState(val userItem: UserItem) : UiState {

    class UpdatingUserItem(userItem: UserItem) : UserUiState(userItem = userItem)

    class UserItemFetched(userItem: UserItem) : UserUiState(userItem = userItem)

}