package ua.university.group

import kotlinx.coroutines.flow.Flow
import ua.university.group.model.Group
import ua.university.network.result.NetworkResult

interface GroupRepository {
    /**
     * Returns all groups of the logged user
     */
    fun getAll(): Flow<List<Group>>

    fun getById(groupId: String): Flow<Group>

    /**
     * Updates all groups of the logged user
     */
    suspend fun updateAll(): NetworkResult<Unit>

    suspend fun updateById(groupId: String): NetworkResult<Unit>

    suspend fun create(name: String, description: String): NetworkResult<Unit>

    suspend fun joinById(groupId: String): NetworkResult<Unit>
}