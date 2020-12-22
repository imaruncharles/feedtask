package com.weexpoindia.task2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.weexpoindia.R
import kotlinx.android.synthetic.main.imageview.view.*

class SliderAdapter(private val context: Context, private val layouts: Array<String>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater.inflate(R.layout.imageview, container, false) as View


        view.findViewById<AppCompatImageView>(R.id.imageView).apply {
            Glide
                .with(this)
                .load(layouts[position])
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .into(this)


        }
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, objects: Any): Boolean {
        return view == objects
    }

    override fun getCount(): Int = layouts.size

    override fun destroyItem(container: ViewGroup, position: Int, objects: Any) {
        val view = objects as View
        container.removeView(view)
    }
}