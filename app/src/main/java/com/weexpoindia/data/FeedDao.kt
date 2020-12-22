package com.weexpoindia.data

import androidx.room.*


@Dao
interface FeedDao{

    @Query("SELECT * FROM feed ORDER BY uid DESC")
    suspend  fun getAll() : List<Feed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeed(feed: Feed) : Long

    @Update
    suspend fun updateFeed(feed: Feed)



}