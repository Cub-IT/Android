package ua.university.group.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.university.group.data.entity.GroupEntity
import ua.university.group.data.entity.PostEntity

@Database(entities = [GroupEntity::class, PostEntity::class], version = 1)
abstract class GroupDatabase: RoomDatabase() {
    abstract val groupDao: GroupDao
    abstract val postDao: PostDao
}