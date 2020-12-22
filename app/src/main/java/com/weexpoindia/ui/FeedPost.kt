package com.weexpoindia.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.dhaval2404.imagepicker.ImagePicker
import com.weexpoindia.R
import com.weexpoindia.data.Feed
import com.weexpoindia.databinding.ActivityFeedPostBinding
import com.weexpoindia.viewmodel.FeedPostViewModel
import com.weexpoindia.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class FeedPost : AppCompatActivity() {

    var imagePath = ""
    lateinit var binding: ActivityFeedPostBinding

    lateinit var viewModel: FeedPostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.post)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(FeedPostViewModel::class.java)

        binding.addImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional) //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.postBtn -> post()
            android.R.id.home -> finish()
        }
        return true
    }


    fun post(){
        if (binding.title.text.toString().trim()==""){
            binding.title.error = "Enter Title"
        }else if (binding.description.text.toString().trim()==""){
            binding.description.error = "Enter Description"
        }else if (imagePath==""){
            Toast.makeText(this, "Add Image", Toast.LENGTH_SHORT).show()
        }else{
            lifecycleScope.launch {
                viewModel.postFeed(
                    applicationContext, Feed(
                        title = binding.title.text.toString().trim(),
                        description = binding.description.text.toString().trim(),
                        imageUrl = imagePath,
                        timestamp = Date().time
                    )
                )
                    setResult(RESULT_OK)
                    finish()

            }
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ( resultCode == RESULT_OK){
            imagePath = data?.data.toString()
            binding.image.setImageURI(Uri.parse(data?.data.toString()))
            Toast.makeText(this, "Image Added", Toast.LENGTH_SHORT).show()
        }
    }



}