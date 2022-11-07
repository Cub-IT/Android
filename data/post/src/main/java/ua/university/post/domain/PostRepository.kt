package ua.university.post.domain

import kotlinx.coroutines.flow.Flow
import ua.university.network.result.NetworkResult
import ua.university.post.domain.model.NewPost
import ua.university.post.domain.model.Post

interface PostRepository {
    fun getByGroup(groupId: String): Flow<List<Post>>

    fun getById(postId: String): Flow<Post>

    suspend fun updateByGroup(groupId: String): NetworkResult<Unit>

    suspend fun create(newPost: NewPost): NetworkResult<Unit>

    suspend fun deleteById(postId: String): NetworkResult<Unit>
}