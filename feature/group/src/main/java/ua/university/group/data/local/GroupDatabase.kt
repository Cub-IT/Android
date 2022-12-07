package ua.university.group.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.university.group.data.entity.GroupEntity

@Database(entities = [GroupEntity::class], version = 1)
abstract class GroupDatabase: RoomDatabase() {
    abstract val dao: GroupDao
}