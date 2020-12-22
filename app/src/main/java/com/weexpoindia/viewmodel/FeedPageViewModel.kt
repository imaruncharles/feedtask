package com.weexpoindia.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weexpoindia.data.AppDatabase
import com.weexpoindia.data.Feed
import kotlinx.coroutines.launch

class FeedPageViewModel : ViewModel() {



    var mutableLive = MutableLiveData<List<Feed>> ()


    fun getAll(context: Context) {
        viewModelScope.launch {
            val list = AppDatabase.getinstance(context).feedDao().getAll();
            mutableLive.value=list
        }
    }

    fun updatePost(context: Context,feed: Feed){
        viewModelScope.launch {
            AppDatabase.getinstance(context).feedDao().updateFeed(feed)
        }
    }
}