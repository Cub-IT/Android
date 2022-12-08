package ua.university.db.post

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {

    abstract val dao: PostDao

}