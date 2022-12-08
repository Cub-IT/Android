package ua.university.group.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ua.university.group.data.entity.GroupEntity

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserGroups(data: List<GroupEntity>)

    @Query("SELECT * FROM groupentity ORDER BY creationDate DESC")
    fun getUserGroups(): Flow<List<GroupEntity>>

    @Query("SELECT * FROM groupentity WHERE id = :groupId")
    fun getUserGroup(groupId: String): Flow<GroupEntity>

    @Query("DELETE FROM groupentity")
    suspend fun deleteUserGroups()
}