package com.jesil.projectmvi.mviapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity): Long

    //[@Query] from getting data from database
    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>
}