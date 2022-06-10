package com.example.core.data.repository

interface UserRepository {

    suspend fun getUserData(userId: String)

    suspend fun editUser(name: String?, email: String?, password: String?)

}