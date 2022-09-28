package ua.university.group.remote.api

import retrofit2.Retrofit
import ua.university.group.Group
import ua.university.group.remote.entry.CreateGroupRequest
import ua.university.group.remote.entry.toGroup
import ua.university.util.result.NetworkResult
import ua.university.util.result.map

class GroupService (
    private val retrofit: Retrofit
) {
    private val groupApi: GroupApi by lazy { retrofit.create(GroupApi::class.java) }

    suspend fun getUserGroups(): NetworkResult<List<Group>> {
        return groupApi.getUserGroups().map { list ->
            list.map { it.toGroup() }
        }
    }

    suspend fun joinGroup(groupCode: String): NetworkResult<Unit> {
        return groupApi.joinGroup(groupCode = groupCode)
    }

    suspend fun createGroup(title: String, description: String): NetworkResult<Unit> {
        val createGroupRequest = CreateGroupRequest(
            title = title,
            description = description
        )
        return groupApi.createGroup(createGroupRequest)
    }

}