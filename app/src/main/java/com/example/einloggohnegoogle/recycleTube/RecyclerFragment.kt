package com.example.einloggohnegoogle.recycleTube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.R

class RecyclerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recyclertube, container, false)

        // YouTube-Video-URLs
        val youtubeUrls1 = listOf(
            "https://www.youtube.com/watch?v=vn7M--xLe6A",
            // Add other URLs as needed
        ) // Vorspeisen

        // Extract video IDs
        val videos = youtubeUrls1.mapIndexed { index, url ->
            val videoId = extractVideoId(url)
            YouTubeVideo("Video ${index + 1}", videoId)
        }

        // Set up RecyclerViews
        val recyclerView1: RecyclerView = view.findViewById(R.id.recyclerView1)
        recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.adapter = YouTubeVideoAdapter(videos)

        // TODO: Set up recyclerView2 and recyclerView3 in a similar manner if needed

        return view
    }

    private fun extractVideoId(youtubeUrl: String): String {
        val pattern = "(?<=watch\\?v=|/videos/|embed/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2Fvideos\u200C\u200B|youtu.be%2F)[^#\\&\\?\\n]*"
        val compiledPattern = Regex(pattern)
        val matcher = compiledPattern.find(youtubeUrl)
        return matcher?.value ?: ""
    }
}

data class YouTubeVideo(val title: String, val videoId: String)
