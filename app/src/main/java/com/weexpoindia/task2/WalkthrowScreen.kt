package com.weexpoindia.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.viewpager.widget.ViewPager
import com.weexpoindia.R
import com.weexpoindia.hide
import com.weexpoindia.show
import kotlinx.android.synthetic.main.activity_walkthrow_screen.*


class WalkthrowScreen : AppCompatActivity() {

    private val DELAY = 5000L

    private lateinit var sliderAdapter: SliderAdapter
    private var dots: Array<TextView?>? = null
    private lateinit var layouts: Array<String>
    private val sliderChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
        }

        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }
    }

    val runnable = Runnable { onNextPage()  }

    val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrow_screen)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Task 2")


        init()
        dataSet()
        interactions()


    }

    private fun init() {

        layouts = arrayOf(
            "https://i.pinimg.com/564x/6f/5f/fb/6f5ffb82a1f9a9f7e478b8a2486831f5.jpg",
            "https://i.pinimg.com/564x/97/a5/51/97a5513d3c512eb382e564ba542d917b.jpg",
            "https://i.pinimg.com/564x/72/c1/a8/72c1a8aabbfe782643c4a5e739ec0ed2.jpg",
            "https://i.pinimg.com/564x/e2/fc/bc/e2fcbc98ceeb7d9316f8b4c889440bf7.jpg",
            "https://i.pinimg.com/564x/3c/52/d3/3c52d31a1b388ea584175f7859fb23e7.jpg",
            "https://i.pinimg.com/564x/4c/32/ee/4c32ee62af42bacec8c50ddfd10ade63.jpg",
            "https://i.pinimg.com/564x/94/cb/29/94cb29d0279e376c6d89fe9a31191f94.jpg"
        )

        sliderAdapter = SliderAdapter(this, layouts)
    }

    private fun dataSet() {
        /**
         * Adding bottom dots
         * */
        addBottomDots(0)

        slider.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun interactions() {
        nextBtn.setOnClickListener {
            onNextPage()
        }
    }

    fun onNextPage(){
        handler.removeCallbacks(runnable)
        val current = getCurrentScreen(+1)
        if (current < layouts.size) {
            slider.currentItem = current
        } else {
            slider.currentItem = 0
        }
        handler.postDelayed(runnable,DELAY)
    }


    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,DELAY)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }



    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size)

        dotsLayout.removeAllViews()
        for (i in 0 until dots!!.size) {
            dots!![i] = TextView(this)
            dots!![i]?.text = Html.fromHtml("&#8226;")
            dots!![i]?.textSize = 40f
            dots!![i]?.setTextColor(resources.getColor(R.color.colorGrey))
            dotsLayout.addView(dots!![i])
        }

        if (dots!!.isNotEmpty()) {
            dots!![currentPage]?.setTextColor(resources.getColor(R.color.design_default_color_error))
        }
    }

    private fun getCurrentScreen(i: Int): Int = slider.currentItem.plus(i)


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId==android.R.id.home){
            finish()
        }

        return true
    }
    }

