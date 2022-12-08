package ua.university.post.data.repository

import ua.university.network.result.NetworkResult
import ua.university.post.data.entity.PostEntity
import ua.university.post.data.remote.api.PostService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postService: PostService,
) {
    suspend fun createPost(groupCode: String, title: String, description: String): NetworkResult<PostEntity> {
        return postService.createPost(groupCode = groupCode, title = title, description = description)
    }

    suspend fun editPost(groupCode: String, postId: String, title: String, description: String): NetworkResult<PostEntity> {
        return postService.editPost(groupCode = groupCode, title = title, description = description, postId = postId)
    }

    suspend fun getPost(groupCode: String, postId: String): NetworkResult<PostEntity> {
        return postService.getPost(groupCode, postId)
    }
}