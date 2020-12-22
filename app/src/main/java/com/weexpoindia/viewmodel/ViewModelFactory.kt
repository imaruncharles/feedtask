package com.weexpoindia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FeedPostViewModel::class.java)){
            return FeedPostViewModel() as T
        }else if (modelClass.isAssignableFrom(FeedPageViewModel::class.java)){
            return FeedPageViewModel() as T
        }

        throw IllegalArgumentException("")
    }
}