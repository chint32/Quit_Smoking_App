package com.chstudios.quitsmokingapp

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_motivational_videos.*


class MotivationalVideosActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivational_videos)

        val motivationalVideos = mutableListOf<String>()
        motivationalVideos.add("3xWT34mEVe8")
        motivationalVideos.add("nMyF2Om_6yY")
        motivationalVideos.add("YRTWEfdegt4")
        motivationalVideos.add("gcosBFS7lA8")
        motivationalVideos.add("v3pjGmtzdUI")
        motivationalVideos.add("QgYLdFyGcSs")
        motivationalVideos.add("8gI2_kvEV44")
        motivationalVideos.add("0mD5kf9lapQ")
        motivationalVideos.add("IaO_0fh0MXo")
        motivationalVideos.add("rEr2ipZxeQA")
        motivationalVideos.add("o3I0mJ2RfU0")
        motivationalVideos.add("dMvrNmtv70Q")
        motivationalVideos.add("qARe5_yWRXQ")
        motivationalVideos.add("Eqrf9JAk-os")
        motivationalVideos.add("RPeNuw8AWa4")
        motivationalVideos.add("zJ9ty5_Z-Lo")
        motivationalVideos.add("RAQtiKx_3ZM")
        motivationalVideos.add("nyIJo7VCdPE")
        motivationalVideos.add("H88MmdsNCXU")
        motivationalVideos.add("3b4RtkaNcFU")

        youtubePlayer.initialize("AIzaSyDiP_J-UDomOqMgyEkCCbhBKGw5QShjkH4",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {

                    youTubePlayer.cueVideo(motivationalVideos.random())
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                }
            })
    }
}