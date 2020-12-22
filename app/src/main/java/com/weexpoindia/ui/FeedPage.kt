package com.weexpoindia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.weexpoindia.R
import com.weexpoindia.data.Feed
import com.weexpoindia.databinding.ActivityFeedPageBinding
import com.weexpoindia.viewmodel.FeedPageViewModel
import com.weexpoindia.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class FeedPage : AppCompatActivity(), FeedAdapter.OnItemClicked {

    private lateinit var binding: ActivityFeedPageBinding
    private val  FEED_POST_REQUEST= 100

    lateinit var viewModel: FeedPageViewModel

    lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Task 1")

        viewModel = ViewModelProvider(this,ViewModelFactory()).get(FeedPageViewModel::class.java)

        binding.feedListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter = FeedAdapter(this)
        binding.feedListRecyclerView.adapter = adapter

        binding.feedPostFAB.setOnClickListener {
            startActivityForResult(Intent(applicationContext,FeedPost::class.java),FEED_POST_REQUEST)
        }

            viewModel.getAll(applicationContext)


        viewModel.mutableLive.observe(this, {

            if(it.size==0){
                binding.emptyPost.visibility = View.VISIBLE
                startActivityForResult(Intent(applicationContext,FeedPost::class.java),FEED_POST_REQUEST)
            }else{
                binding.emptyPost.visibility = View.GONE
            }

            adapter.loadData(list = it)

        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==FEED_POST_REQUEST && resultCode == RESULT_OK){

                viewModel.getAll(applicationContext)

        }
    }

    override fun postLike(feed: Feed) {
        viewModel.updatePost(applicationContext,feed)
    }

    override fun postBookmark(feed: Feed) {
        viewModel.updatePost(applicationContext,feed)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==android.R.id.home){
            finish()
        }

        return true
    }


}