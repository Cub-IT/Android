package ua.university.post.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import ua.university.network.result.NetworkResult
import ua.university.network.result.Result
import ua.university.network.result.onFailure
import ua.university.post.data.local.PostDao
import ua.university.post.data.local.toPost
import ua.university.post.data.local.toPostList
import ua.university.post.data.remote.api.PostService
import ua.university.post.data.remote.entry.toPostEntity
import ua.university.post.data.remote.entry.toPostEntityList
import ua.university.post.domain.PostRepository
import ua.university.post.domain.model.NewPost
import ua.university.post.domain.model.Post
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val postService: PostService,
) : PostRepository {
    override fun getByGroup(groupId: String): Flow<List<Post>> {
        return postDao.getByGroup(groupId = groupId).mapLatest { it.toPostList() }
    }

    override fun getById(postId: String): Flow<Post> {
        return postDao.getById(postId = postId).mapLatest { it.toPost()}
    }

    override suspend fun updateByGroup(groupId: String): NetworkResult<Unit> {
        val response = postService.getByGroup(groupId = groupId).onFailure { return it }
        postDao.deleteByGroup(groupId = groupId)
        postDao.insert(response.toPostEntityList())

        return Result.Success(Unit)
    }

    override suspend fun create(newPost: NewPost): NetworkResult<Unit> {
        val response = postService.create(newPost = newPost).onFailure { return  it }
        postDao.insert(listOf(response.toPostEntity()))

        return Result.Success(Unit)
    }

    override suspend fun deleteById(postId: String): NetworkResult<Unit> {
        postService.deleteById(postId = postId).onFailure { return it }
        postDao.deleteById(postId = postId)

        return Result.Success(Unit)
    }
}