package ua.university.post.data.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import ua.university.network.result.NetworkResult
import ua.university.post.data.entity.PostEntity
import ua.university.post.data.remote.dto.CreatePostRequest

interface PostApi {
    @POST("post/class/{classId}/new")
    suspend fun createPost(
        @Path("classId") groupCode: String,
        @Body createPostRequest: CreatePostRequest
    ): NetworkResult<List<PostEntity>>

    @PATCH("post/class/{classId}/modify/{postId}")
    suspend fun editPost(
        @Path("classId") groupCode: String,
        @Path("postId") postId: String,
        @Body createPostRequest: CreatePostRequest
    ): NetworkResult<List<PostEntity>>

    @GET("post/class/{classId}/get/{postId}")
    suspend fun getPost(
        @Path("classId") groupCode: String,
        @Path("postId") postId: String,
    ): NetworkResult<List<PostEntity>>
}