package com.weexpoindia.ui

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weexpoindia.Util
import com.weexpoindia.data.Feed
import com.weexpoindia.databinding.FeedViewBinding


class FeedAdapter(val onItemClicked: OnItemClicked) : RecyclerView.Adapter<FeedAdapter.CustomViewHolder>(){

   var  feedList : List<Feed> = ArrayList()


    class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(FeedViewBinding.inflate(LayoutInflater.from(parent.context),parent,false).root)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        FeedViewBinding.bind(holder.itemView).apply {
            FeedViewBinding.bind(holder.itemView).title.text = feedList[position].title
            FeedViewBinding.bind(holder.itemView).description.text = feedList[position].description
            FeedViewBinding.bind(holder.itemView).imageView.setImageURI(Uri.parse(feedList[position].imageUrl))
            FeedViewBinding.bind(holder.itemView).timestampView.text = Util.timeAgo(feedList[position].timestamp)

            if (feedList[position].like){
                FeedViewBinding.bind(holder.itemView).likeBtn.setColorFilter(Color.BLUE)
            }else{
                FeedViewBinding.bind(holder.itemView).likeBtn.setColorFilter(Color.GRAY)
            }

            if (feedList[position].bookmark){
                FeedViewBinding.bind(holder.itemView).bookmarkBtn.setColorFilter(Color.BLUE)
            }else{
                FeedViewBinding.bind(holder.itemView).bookmarkBtn.setColorFilter(Color.GRAY)
            }

            FeedViewBinding.bind(holder.itemView).likeBtn.setOnClickListener {
                if (feedList[position].like){
                    feedList[position].like = false
                    onItemClicked.postLike(feedList[position])
                    notifyItemChanged(position)
                }else{
                    feedList[position].like = true
                    onItemClicked.postLike(feedList[position])
                    notifyItemChanged(position)
                }
            }

            FeedViewBinding.bind(holder.itemView).bookmarkBtn.setOnClickListener {
                if (feedList[position].bookmark){
                    feedList[position].bookmark = false
                    onItemClicked.postBookmark(feedList[position])
                    notifyItemChanged(position)
                }else{
                    feedList[position].bookmark= true
                    onItemClicked.postBookmark(feedList[position])
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount() = feedList.size

    fun loadData(list: List<Feed>)
    {
        feedList = list
        notifyDataSetChanged()
    }

    interface OnItemClicked{
        fun postLike(feed: Feed)
        fun postBookmark(feed: Feed)
    }
}