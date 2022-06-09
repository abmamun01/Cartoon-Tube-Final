package com.mamunsproject.youtubekids.adapter

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cartoontuberefinefinal.Model.ResponseVideo
import com.example.cartoontuberefinefinal.Model.Video
import com.mamunsproject.youtubekids.PlayerActivity
import com.mamunsproject.youtubekids.R


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    private var videoList = ArrayList<Video>()


    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var textViewTitle: TextView = itemView.findViewById(R.id.tv_item_title);
        var imageViewThumbnail: ImageView = itemView.findViewById(R.id.iv_item_cover)
        var relativeTransitionId: RelativeLayout = itemView.findViewById(R.id.relativeTranstionId)
        var medium_ads: LinearLayout = itemView.findViewById(R.id.medium_rectangle_holder)
        var banner2__holder: LinearLayout = itemView.findViewById(R.id.banner2__holder)
        var progressBar: ProgressBar = itemView.findViewById(R.id.layout_progressbar)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {

        return VideoHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {

        val video: Video = videoList.get(position)

        if (video != null) {

            holder.progressBar.visibility = View.VISIBLE

            val title = video.snippet.title
            holder.textViewTitle.text = video.snippet.title
            Log.d("INADAPTERSITUATION", "text $title")

            if (video.snippet.thumbnails != null) {


                var typeThumbnail = video.snippet.thumbnails.high
                if (typeThumbnail == null) typeThumbnail = video.snippet.thumbnails.medium
                if (typeThumbnail == null) typeThumbnail = video.snippet.thumbnails.standard
                if (typeThumbnail == null) typeThumbnail = video.snippet.thumbnails.high

                Log.d("INADAPTERSITUATION", "No Image Available For This Position $typeThumbnail")

                if (typeThumbnail != null) {

                    Glide.with(holder.itemView).load(typeThumbnail.url)
                        .into(holder.imageViewThumbnail)

                    holder.progressBar.visibility = View.INVISIBLE


                } else {
                    //  Toast.makeText(context, "No Image Available!", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "No Image Available For This Position $position")
                }


            }


            //  Glide.with(context).load(t.url).into(holder.imageViewThumbnail);
            holder.itemView.setOnClickListener {
                val id_ = video.contentDetails.videoId
                val title_ = video.snippet.title
                val intent = Intent(holder.itemView.context, PlayerActivity::class.java)
                intent.putExtra("videoid", id_)
                intent.putExtra("title_id", title_)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                holder.itemView.context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return videoList.size
        Log.d("INADAPTERSITUATION", "text ${videoList.size}")

    }


    //Adapter j data pathabo ai fun r maddome
    fun setData(videoList: ResponseVideo) {

        this.videoList = videoList.items
        notifyDataSetChanged()

    }
}



