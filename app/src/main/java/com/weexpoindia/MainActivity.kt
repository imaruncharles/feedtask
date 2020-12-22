package com.weexpoindia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weexpoindia.databinding.ActivityMainBinding
import com.weexpoindia.task2.WalkthrowScreen
import com.weexpoindia.ui.FeedPage

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.task1Btn.setOnClickListener {
            startActivity(Intent(this,FeedPage::class.java))
        }

        binding.task2Btn.setOnClickListener {
            startActivity(Intent(this,WalkthrowScreen::class.java))
        }
    }
}