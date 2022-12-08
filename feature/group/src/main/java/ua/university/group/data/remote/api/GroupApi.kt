package ua.university.group.data.remote.api

import retrofit2.http.*
import ua.university.group.data.entity.GroupEntity
import ua.university.group.data.remote.dto.CreateGroupRequest
import ua.university.group.data.remote.dto.JoinGroupRequest
import ua.university.network.result.NetworkResult

interface GroupApi {
    @GET("class/get")
    suspend fun getUserGroups(): NetworkResult<ArrayList<GroupEntity>>

    @GET("class/get/{classId}")
    suspend fun getUserGroup(
        @Path("classId") classId: String
    ): NetworkResult<GroupEntity>

    @PATCH("class/add/user")
    suspend fun joinGroup(
        @Body joinGroupRequest: JoinGroupRequest
    ): NetworkResult<GroupEntity>

    @POST("class/new")
    suspend fun createGroup(
        @Body createGroupRequest: CreateGroupRequest
    ): NetworkResult<GroupEntity>

    @DELETE("class/delete/{classId}")
    suspend fun deleteGroup(
        @Path("classId") classId: String
    ): NetworkResult<Unit>
}