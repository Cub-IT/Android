package ua.university.db.group

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GroupEntity::class], version = 1)
abstract class GroupDatabase: RoomDatabase() {

    abstract val dao: GroupDao

}