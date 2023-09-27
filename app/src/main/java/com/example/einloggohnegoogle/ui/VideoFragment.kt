package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.data.YouTubeVideo
import com.example.einloggohnegoogle.adapter.YouTubeVideoAdapter
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.example.einloggohnegoogle.databinding.FragmentNeuesRezeptBinding
import com.google.firebase.firestore.FirebaseFirestore


class VideoFragment : Fragment() {


    private val firestore = FirebaseFirestore.getInstance()


    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentDataBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        // YouTube-Video-URLs Todo youtubedaten auslagern und 3 strings hier rein.
        val youtubeUrls1 = listOf(
            "https://www.youtube.com/watch?v=vn7M--xLe6A",
            "https://www.youtube.com/watch?v=zF6DVnqeLGg",
            "https://www.youtube.com/watch?v=FyFTKC4t5y4",
            "https://www.youtube.com/watch?v=XF3fMth4SNY&t=47s",
            "https://www.youtube.com/watch?v=mX7UqGfP3vg",
            "https://www.youtube.com/watch?v=fCxo1mdU42w&t=10s",
            "https://www.youtube.com/watch?v=tzUt7FEwxQs&t=369s",
            "https://www.youtube.com/watch?v=pTUOxImCAGQ&list=PLUmbHzQfets10c7Sm51g51XuhxRhlTBPf",
            "https://www.youtube.com/watch?v=1xFNWeI_Xsc&list=PLUmbHzQfets10c7Sm51g51XuhxRhlTBPf&index=2",
            "https://www.youtube.com/watch?v=WzM-AFLkbF8&list=PLUmbHzQfets10c7Sm51g51XuhxRhlTBPf&index=3",
            "https://www.youtube.com/watch?v=u_jes-C8v6U",
            "https://www.youtube.com/watch?v=vCP3W7jcqKU&list=PLwRIc-_e4fKbbG8-pMtfZeNaKdxIfZurk&index=8",
            "https://www.youtube.com/watch?v=UkVSh4MbUaY",
            "https://www.youtube.com/watch?v=bVmnfZNxJuc",
            "https://www.youtube.com/watch?v=gx-GSXBjAPY"
        )//Vorspeisen
        val youtubeUrls2 = listOf(
            "https://www.youtube.com/watch?v=IKfOo36lv2Q",
            "https://www.youtube.com/watch?v=3g-tJgdIX7w&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=3",
            "https://www.youtube.com/watch?v=9PqcWj2fM9U&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=4",
            "https://www.youtube.com/watch?v=7dt_7PmhFOs&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=5",
            "https://www.youtube.com/watch?v=Rxrw3e2XbNU&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=6",
            "https://www.youtube.com/watch?v=w__EVABBnPo&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=7",
            "https://www.youtube.com/watch?v=cjYolgBIW7A&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=8",
            "https://www.youtube.com/watch?v=-igX87izOic&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=9",
            "https://www.youtube.com/watch?v=mMJvVHdvZAg&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=10",
            "https://www.youtube.com/watch?v=Km99FtFEr2w&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O",
            "https://www.youtube.com/watch?v=PDipEx2l-gg",
            "https://www.youtube.com/watch?v=MydaQvWHknI",
            "https://www.youtube.com/watch?v=eyfDl8gRwG0&t=8s",
            "https://www.youtube.com/watch?v=J0ln81fyE8w",
            "https://www.youtube.com/watch?v=zQfd_bpXsQg&list=PLUmbHzQfets2EdqMUyyapBVkcZTsmPq3O&index=2",
            "https://www.youtube.com/watch?v=AdvUcoaXm24&t=13s",
            "https://www.youtube.com/watch?v=eyfDl8gRwG0",
            "https://www.youtube.com/watch?v=UNMNZtTQA4o",
            "https://www.youtube.com/watch?v=gx-GSXBjAPY"


        )//Gerichte
        val youtubeUrls3 = listOf(
            "https://www.youtube.com/watch?v=qZe2MS3TLK8&t=6s",
            "https://www.youtube.com/watch?v=A6WXl2Qd0PE&list=PLUmbHzQfets0JzlLGAEWy8XvbtO-JJxO3",
            "https://www.youtube.com/watch?v=3kk1I4DLCXg&t=10s",
            "https://www.youtube.com/watch?v=xkkIqDULP4U",
            "https://www.youtube.com/watch?v=V2lX5N3NF2w",
            "https://www.youtube.com/watch?v=dGy5IPUZmt8",
            "https://www.youtube.com/watch?v=qZe2MS3TLK8",
            ""
        )//Süßes


        // Extract video IDs
        val videos = youtubeUrls1.mapIndexed { index, url ->
            val videoId = extractVideoId(url)
            YouTubeVideo("Video ${index + 1}", videoId)
        }
        val videos2 = youtubeUrls2.mapIndexed { index, url ->
            val videoId = extractVideoId(url)
            YouTubeVideo("Video ${index + 1}", videoId)
        }
        val videos3 = youtubeUrls3.mapIndexed { index, url ->
            val videoId = extractVideoId(url)
            YouTubeVideo("Video ${index + 1}", videoId)
        }

        val recyclerView1: RecyclerView = view.findViewById(R.id.recyclerView1)
        recyclerView1.layoutManager = LinearLayoutManager(requireContext())
        recyclerView1.adapter = YouTubeVideoAdapter(videos)

        val recyclerView2: RecyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        recyclerView2.adapter = YouTubeVideoAdapter(videos2)

        val recyclerView3: RecyclerView = view.findViewById(R.id.recyclerView3)
        recyclerView3.layoutManager = LinearLayoutManager(requireContext())
        recyclerView3.adapter = YouTubeVideoAdapter(videos3)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Finde den ImageView durch seine ID
        val videoBackButton: ImageView = view.findViewById(R.id.VideoBackBTN)

        // Füge dem ImageView eine Klickaktion hinzu
        videoBackButton.setOnClickListener {
            // Hier kannst du die Aktion ausführen, die passieren soll, wenn der Button geklickt wird
            // Zum Beispiel den Nutzer zurücknavigieren
            activity?.onBackPressed()
        }
    }

    private fun extractVideoId(youtubeUrl: String): String {
        val pattern = "(?<=watch\\?v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2Fvideos\u200C\u200B|youtu.be%2F)[^#&?\\n]*"
        val compiledPattern = Regex(pattern)
        val matcher = compiledPattern.find(youtubeUrl)
        return matcher?.value ?: ""
    }
}


