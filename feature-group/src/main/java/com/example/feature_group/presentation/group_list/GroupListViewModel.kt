package com.example.feature_group.presentation.group_list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.BaseViewModel
import com.example.feature_group.presentation.group_list.item.GroupListItem
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
                            GroupListItem(
                                name = "Group name 1",
                                ownerName = "Teacher Name 1",
                                coverColor = Color(0xFF0277BD)
                            ),
                            GroupListItem(
                                name = "Group name 2",
                                ownerName = "Teacher Name 2",
                                coverColor = Color(0xFF3B79E8)
                            ),
                            GroupListItem(
                                name = "Group name 3",
                                ownerName = "Teacher Name 3",
                                coverColor = Color(0xFF32AC71)
                            ),
                            GroupListItem(
                                name = "Group name 4",
                                ownerName = "Teacher Name 4",
                                coverColor = Color(0xFF566E7A)
                            ),
                            GroupListItem(
                                name = "Group name 5",
                                ownerName = "Teacher Name 5",
                                coverColor = Color(0xFFD91A60)
                            ),
                            GroupListItem(
                                name = "Group name 6",
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