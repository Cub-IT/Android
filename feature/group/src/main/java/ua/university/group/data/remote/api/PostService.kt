package ua.university.group.data.remote.api

import retrofit2.Retrofit
import ua.university.group.data.entity.PostEntity
import ua.university.network.result.NetworkResult
import javax.inject.Inject

class PostService @Inject constructor(
    private val retrofit: Retrofit
) {
    private val postApi: PostApi by lazy { retrofit.create(PostApi::class.java) }

    suspend fun getGroupPosts(groupCode: String): NetworkResult<List<PostEntity>> {
        return postApi.getGroupPosts(groupCode = groupCode)
    }
}