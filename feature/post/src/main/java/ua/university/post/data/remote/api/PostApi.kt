package ua.university.post.data.remote.api

import retrofit2.http.Body
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
}