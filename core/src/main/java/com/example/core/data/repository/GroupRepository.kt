package com.example.core.data.repository

interface GroupRepository {

    suspend fun getUserGroups(userId: String)

    suspend fun getGroupData()

    suspend fun createGroup()

    suspend fun editGroup()

    suspend fun deleteGroup()



    suspend fun addParticipant()

    suspend fun removeParticipant()

}