package ua.university.post.data.remote.api

import retrofit2.Retrofit
import retrofit2.http.Path
import ua.university.network.result.NetworkResult
import ua.university.network.result.map
import ua.university.post.data.entity.PostEntity
import ua.university.post.data.remote.dto.CreatePostRequest
import javax.inject.Inject

class PostService @Inject constructor(
    private val retrofit: Retrofit
) {
    private val postApi: PostApi by lazy { retrofit.create(PostApi::class.java) }

    suspend fun createPost(groupCode: String, title: String, description: String): NetworkResult<PostEntity> {
        val createPostRequest = CreatePostRequest(
            title = title,
            description = description,
        )
        return postApi.createPost(groupCode, createPostRequest).map { it.first() }
    }

    suspend fun editPost(groupCode: String, postId: String, title: String, description: String): NetworkResult<PostEntity> {
        val editPostRequest = CreatePostRequest(
            title = title,
            description = description,
        )
        return postApi.editPost(groupCode, postId, editPostRequest).map { it.first() }
    }

    suspend fun getPost(groupCode: String, postId: String): NetworkResult<PostEntity> {
        return postApi.getPost(groupCode, postId).map { it.first() }
    }
}