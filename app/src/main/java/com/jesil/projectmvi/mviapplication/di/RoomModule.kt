package com.jesil.projectmvi.mviapplication.di

import android.content.Context
import androidx.room.Room
import com.jesil.projectmvi.mviapplication.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context) =
         Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase) =
            blogDatabase.blogDao()
}