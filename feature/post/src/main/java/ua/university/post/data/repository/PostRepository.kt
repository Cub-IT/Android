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
}