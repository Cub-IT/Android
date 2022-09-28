package ua.university.group.remote.api

import retrofit2.http.*
import ua.university.group.remote.entry.CreateGroupRequest
import ua.university.group.remote.entry.GetUserGroupsResponse
import ua.university.util.result.NetworkResult

interface GroupApi {
    @GET("groups")
    suspend fun getUserGroups(): NetworkResult<GetUserGroupsResponse>

    @PATCH("join/{groupCode}")
    suspend fun joinGroup(
        @Path("groupCode") groupCode: String
    ): NetworkResult<Unit>

    @POST("/cub-it/rest/api/v1/group/new")
    suspend fun createGroup(
        @Body createGroupRequest: CreateGroupRequest
    ): NetworkResult<Unit>
}