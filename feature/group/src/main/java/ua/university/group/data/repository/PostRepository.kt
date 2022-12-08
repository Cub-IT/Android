package ua.university.group.data.repository

import kotlinx.coroutines.flow.Flow
import ua.university.group.data.entity.PostEntity
import ua.university.group.data.local.PostDao
import ua.university.group.data.remote.api.PostService
import ua.university.network.result.NetworkResult
import ua.university.network.result.Result
import ua.university.network.result.onFailure
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postService: PostService,
    private val postDao: PostDao,
) {
    fun getGroupPosts(groupId: String): Flow<List<PostEntity>> {
        return postDao.getGroupPosts(groupId = groupId)
    }

    suspend fun updateGroupPosts(groupId: String): NetworkResult<Unit> {
        val posts = postService.getGroupPosts(groupCode = groupId)
            .onFailure { return it }

        postDao.deleteGroupPosts(groupId = groupId)
        postDao.insertGroupPosts(posts)

        return Result.Success(Unit)
    }
}