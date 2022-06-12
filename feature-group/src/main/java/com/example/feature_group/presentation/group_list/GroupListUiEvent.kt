package com.example.feature_group.presentation.group_list

import com.example.core.presentation.UiEvent

sealed class GroupListUiEvent : UiEvent() {

    object LoadGroups : GroupListUiEvent()

}