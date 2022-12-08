package ua.university.db.group

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserGroups(data: List<GroupEntity>)

    @Query("SELECT * FROM groupentity")
    fun getUserGroups(): Flow<List<GroupEntity>>

    @Query("SELECT * FROM groupentity WHERE id = :groupId")
    fun getUserGroup(groupId: String): Flow<GroupEntity>

    @Query("DELETE FROM groupentity")
    suspend fun deleteUserGroups()

}