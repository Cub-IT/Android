package ua.university.group

import kotlinx.coroutines.flow.Flow
import ua.university.group.local.GroupSource
import ua.university.group.remote.api.GroupService
import ua.university.util.result.NetworkResult
import ua.university.util.result.Result
import ua.university.util.result.onFailure

class GroupRepository(
    private val groupService: GroupService,
    private val groupSource: GroupSource
) {
    fun getUserGroups(): Flow<List<Group>> {
        return groupSource.getUserGroups()
    }

    suspend fun getUserGroupsSuspend(): List<Group> {
        return groupSource.getUserGroupsSuspend()
    }

    fun getUserGroup(groupId: String): Flow<Group> {
        return groupSource.getUserGroup(groupId = groupId)
    }

    suspend fun getUserGroupSuspend(groupId: String): Group {
        return groupSource.getUserGroupSuspend(groupId = groupId)
    }

    suspend fun updateUserGroups(): NetworkResult<Unit> {
        val groups = groupService.getUserGroups().onFailure { return it }

        groupSource.deleteUserGroups()
        groupSource.insertUserGroups(groups)

        return Result.Success(Unit)
    }

    suspend fun createGroup(name: String, description: String): NetworkResult<Unit> {
        return groupService.createGroup(title = name, description = description)
    }

    suspend fun joinToGroup(groupId: String): NetworkResult<Unit> {
        return groupService.joinGroup(groupCode = groupId)
    }
}