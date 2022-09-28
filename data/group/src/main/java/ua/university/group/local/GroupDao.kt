package ua.university.group.local

import kotlinx.coroutines.flow.Flow
import ua.university.group.Group

interface GroupDao {
    suspend fun insertUserGroups(data: List<Group>)

    fun getUserGroups(): Flow<List<Group>>

    suspend fun getUserGroupsSuspend(): List<Group>

    fun getUserGroup(groupId: String): Flow<Group>

    suspend fun getUserGroupSuspend(groupId: String): Group

    suspend fun deleteUserGroups()
}