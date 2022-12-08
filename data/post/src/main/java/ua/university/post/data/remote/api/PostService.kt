package ua.university.post.data.remote.api

import ua.university.network.result.NetworkResult
import ua.university.post.data.remote.entry.CreateGroupResponse
import ua.university.post.data.remote.entry.GetByGroupResponse
import ua.university.post.domain.model.NewPost
import javax.inject.Inject

class PostService @Inject constructor(
    //private val retrofit
) {
    private val postApi: PostApi by lazy { TODO() }

    suspend fun create(newPost: NewPost): NetworkResult<CreateGroupResponse> {
        return postApi.create()
    }

    suspend fun getByGroup(groupId: String): NetworkResult<GetByGroupResponse> {
        return postApi.getByGroup(groupId = groupId)
    }

    suspend fun deleteById(postId: String): NetworkResult<Unit> {
        return postApi.deleteById(postId = postId)
    }
}