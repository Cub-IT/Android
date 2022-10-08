package ua.university.group

import kotlinx.coroutines.flow.Flow
import ua.university.group.model.Group
import ua.university.network.result.NetworkResult

interface GroupRepository {
    fun getUserGroups(): Flow<List<Group>>

    suspend fun getUserGroupsSuspend(): List<Group>

    fun getUserGroup(groupId: String): Flow<Group>

    suspend fun getUserGroupSuspend(groupId: String): Group

    suspend fun updateUserGroups(): NetworkResult<Unit>

    suspend fun createGroup(name: String, description: String): NetworkResult<Unit>

    suspend fun joinToGroup(groupId: String): NetworkResult<Unit>
}