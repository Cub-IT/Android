package com.example.core.data.repository

interface PostRepository {

    suspend fun getPosts()

    suspend fun getPost()

}