package com.weexpoindia.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weexpoindia.data.AppDatabase
import com.weexpoindia.data.Feed

class FeedPostViewModel : ViewModel(){


   suspend fun postFeed(context: Context,feed: Feed) : Long {
           return AppDatabase.getinstance(context).feedDao().insertFeed(feed)
    }
}