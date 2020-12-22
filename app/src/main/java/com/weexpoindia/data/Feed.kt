package com.weexpoindia.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "feed")
data class Feed(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "like") var like: Boolean = false,
    @ColumnInfo(name = "bookmark") var bookmark: Boolean = false
)