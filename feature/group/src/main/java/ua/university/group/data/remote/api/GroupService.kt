package ua.university.group.data.remote.api

import retrofit2.Retrofit
import ua.university.group.data.entity.GroupEntity
import ua.university.group.data.remote.dto.CreateGroupRequest
import ua.university.group.data.remote.dto.JoinGroupRequest
import ua.university.network.result.NetworkResult
import ua.university.network.result.map
import javax.inject.Inject

class GroupService @Inject constructor(
    private val retrofit: Retrofit
) {
    private val groupApi: GroupApi by lazy { retrofit.create(GroupApi::class.java) }

    suspend fun getUserGroups(): NetworkResult<ArrayList<GroupEntity>> {
        return groupApi.getUserGroups()
    }

    suspend fun getUserGroup(classId: String): NetworkResult<GroupEntity> {
        return groupApi.getUserGroup(classId = classId).map { it.first() }
    }

    suspend fun joinGroup(groupCode: String): NetworkResult<GroupEntity> {
        val joinGroupRequest = JoinGroupRequest(code = groupCode)
        return groupApi.joinGroup(joinGroupRequest).map { it.first() }
    }

    suspend fun createGroup(title: String, description: String): NetworkResult<GroupEntity> {
        val createGroupRequest = CreateGroupRequest(
            title = title,
            description = description
        )
        return groupApi.createGroup(createGroupRequest).map { it.first() }
    }

    suspend fun editGroup(groupId: String, title: String, description: String): NetworkResult<GroupEntity> {
        val editGroupRequest = CreateGroupRequest(
            title = title,
            description = description
        )
        return groupApi.editGroup(groupId, editGroupRequest).map { it.first() }
    }

    suspend fun deleteGroup(classId: String): NetworkResult<Unit> {
        return groupApi.deleteGroup(classId = classId)
    }
}