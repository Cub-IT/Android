package ua.university.cubit.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.university.group.data.local.GroupDao
import ua.university.group.data.local.GroupDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideGroupDatabase(
        app: Application
    ): GroupDatabase {
        return Room.databaseBuilder(
            app,
            GroupDatabase::class.java,
            "group_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGroupDao(
        groupDatabase: GroupDatabase
    ): GroupDao {
        return groupDatabase.dao
    }

    /*@Provides
    @Singleton
    fun providePostDatabase(
        app: Application
    ): PostDatabase {
        return Room.databaseBuilder(
            app,
            PostDatabase::class.java,
            "post_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(
        postDatabase: PostDatabase
    ): PostDao {
        return postDatabase.dao
    }*/

}