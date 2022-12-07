package ua.university.group.data.repository

import kotlinx.coroutines.flow.Flow
import ua.university.group.data.entity.GroupEntity
import ua.university.group.data.local.GroupDao
import ua.university.group.data.remote.api.GroupService
import ua.university.network.result.NetworkResult
import ua.university.network.result.Result
import ua.university.network.result.onFailure
import javax.inject.Inject

class GroupRepository @Inject constructor(
    private val groupService: GroupService,
    private val groupDao: GroupDao,
) {
    fun getUserGroups(): Flow<List<GroupEntity>> {
        return groupDao.getUserGroups()
    }

    suspend fun updateUserGroups(): NetworkResult<Unit> {
        val groups = groupService.getUserGroups().onFailure { return it }

        groupDao.deleteUserGroups()
        groupDao.insertUserGroups(groups)

        return Result.Success(Unit)
    }

    suspend fun createGroup(name: String, description: String): NetworkResult<String> {
        return groupService.createGroup(title = name, description = description)
    }

    suspend fun joinToGroup(groupId: String): NetworkResult<String> {
        return groupService.joinGroup(groupCode = groupId)
    }
}