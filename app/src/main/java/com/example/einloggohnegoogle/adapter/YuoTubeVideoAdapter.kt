package com.example.einloggohnegoogle.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.einloggohnegoogle.R

import com.example.einloggohnegoogle.data.YouTubeVideo


class YouTubeVideoAdapter(private val videos: List<YouTubeVideo>) :
    RecyclerView.Adapter<YouTubeVideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_youtube_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.videoTitleTextView.text = video.title

        // Lade ein Vorschaubild des YouTube-Videos
        val thumbnailUrl = "https://img.youtube.com/vi/${video.videoId}/0.jpg"
        Glide.with(holder.itemView).load(thumbnailUrl).into(holder.videoThumbnailImageView)

        // Öffne das Video in der YouTube-App, wenn auf das Element geklickt wird
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${video.videoId}"))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitleTextView: TextView = itemView.findViewById(R.id.videoTitleTextView)
        val videoThumbnailImageView: ImageView = itemView.findViewById(R.id.videoThumbnailImageView)
    }
}


