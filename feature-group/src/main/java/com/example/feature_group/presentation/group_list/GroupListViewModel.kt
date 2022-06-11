package com.example.feature_group.presentation.group_list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.feature_group.presentation.common.item.GroupItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GroupListViewModel(

) : BaseViewModel<GroupListUiEvent, GroupListUiState>() {

    override fun createInitialState(): GroupListUiState {
        return GroupListUiState.LoadingGroups
    }

    override fun handleEvent(event: GroupListUiEvent) {
        when (event) {
            GroupListUiEvent.LoadGroups -> {
                viewModelScope.launch {
                    delay(2000)
                    _uiState.value = GroupListUiState.GroupsFetched(
                        groups = listOf(
                            GroupItem(
                                name = "Group name 1",
                                description = "Here is a description",
                                ownerName = "Teacher Name 1",
                                coverColor = Color(0xFF0277BD)
                            ),
                            GroupItem(
                                name = "Group name 2",
                                description = "Here is a description",
                                ownerName = "Teacher Name 2",
                                coverColor = Color(0xFF3B79E8)
                            ),
                            GroupItem(
                                name = "Group name 3",
                                description = "Here is a description",
                                ownerName = "Teacher Name 3",
                                coverColor = Color(0xFF32AC71)
                            ),
                            GroupItem(
                                name = "Group name 4",
                                description = "Here is a description",
                                ownerName = "Teacher Name 4",
                                coverColor = Color(0xFF566E7A)
                            ),
                            GroupItem(
                                name = "Group name 5",
                                description = "Here is a description",
                                ownerName = "Teacher Name 5",
                                coverColor = Color(0xFFD91A60)
                            ),
                            GroupItem(
                                name = "Group name 6",
                                description = "Here is a description",
                                ownerName = "Teacher Name 6",
                                coverColor = Color(0xFF02579A)
                            )
                        )
                    )
                }
            }
        }
    }

}