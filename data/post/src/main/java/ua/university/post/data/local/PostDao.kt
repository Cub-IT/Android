package ua.university.post.data.local

import kotlinx.coroutines.flow.Flow

interface PostDao {
    suspend fun insert(data: List<PostEntity>)

    fun getByGroup(groupId: String): Flow<List<PostEntity>>

    fun getById(postId: String): Flow<PostEntity>

    suspend fun deleteByGroup(groupId: String)

    suspend fun deleteById(postId: String)
}