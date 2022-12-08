package ua.university.group.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import ua.university.group.data.entity.PostEntity
import ua.university.network.result.NetworkResult

interface PostApi {
    @GET("post/class/{groupCode}/get")
    suspend fun getGroupPosts(
        @Path("groupCode") groupCode: String
    ): NetworkResult<List<PostEntity>>
}