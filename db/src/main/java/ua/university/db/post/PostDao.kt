package ua.university.db.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupPosts(data: List<PostEntity>)

    @Query("SELECT * FROM postentity WHERE groupId = :groupId")
    fun getGroupPosts(groupId: String): Flow<List<PostEntity>>

    @Query("SELECT * FROM postentity WHERE id = :postId")
    suspend fun getPost(postId: String): PostEntity

    @Query("SELECT * FROM postentity WHERE groupId = :groupId")
    suspend fun getGroupPostsSuspend(groupId: String): List<PostEntity>

    @Query("DELETE FROM postentity WHERE groupId = :groupId")
    suspend fun deleteGroupPosts(groupId: String)

}