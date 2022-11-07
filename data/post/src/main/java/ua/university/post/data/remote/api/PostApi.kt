package ua.university.post.data.remote.api

import ua.university.network.result.NetworkResult
import ua.university.post.data.remote.entry.CreateGroupResponse
import ua.university.post.data.remote.entry.GetByGroupResponse

interface PostApi {
    suspend fun create(): NetworkResult<CreateGroupResponse>

    suspend fun getByGroup(groupId: String): NetworkResult<GetByGroupResponse>

    suspend fun deleteById(postId: String): NetworkResult<Unit>
}