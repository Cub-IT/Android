package ua.university.group.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ua.university.group.data.entity.PostEntity

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupPosts(data: List<PostEntity>)

    @Query("SELECT * FROM postentity WHERE classId = :groupId")
    fun getGroupPosts(groupId: String): Flow<List<PostEntity>>

    @Query("DELETE FROM postentity WHERE classId = :groupId")
    suspend fun deleteGroupPosts(groupId: String)
}