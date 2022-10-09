package ua.university.post

import kotlinx.coroutines.flow.Flow
import ua.university.network.result.NetworkResult
import ua.university.post.model.Post

interface PostRepository {
    fun getByGroup(groupId: String): Flow<List<Post>>

    fun getById(postId: String): Flow<Post>

    suspend fun updateByGroup(groupId: String): NetworkResult<Unit>

    suspend fun create(): NetworkResult<Unit>

    suspend fun deleteById(postId: String): NetworkResult<Unit>
}