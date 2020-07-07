package com.example.projettdm.Adapters



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.R
import kotlinx.android.synthetic.main.fragment_information.view.*
import kotlinx.android.synthetic.main.youtube_item.view.*


class YoutubeAdapter(ls: MutableList<String>, cntx:Context) : RecyclerView.Adapter<YoutubeAdapter.MYViewHolder>() {
    var liste: MutableList<String> = ls
    var con : Context = cntx




    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun set(book:String){

            /*
            val yt_listener = object : YouTubePlayer.OnInitializedListener{
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(book)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(con," video wrong",Toast.LENGTH_SHORT).show()
                }

            }
            itemView.play_youtube.setOnClickListener {
                itemView.youtube_view.initialize(key,yt_listener)
            }*/

            val frameVideo =
                "<html><body>Video From YouTube<br><iframe width=\"100%\" height=\"400\" src=\"https://www.youtube.com/embed/$book\" frameborder=\"0\" allowfullscreen></iframe></body></html>"

            itemView.youtube_view.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    return false
                }
            }
            val webSettings: WebSettings = itemView.youtube_view.settings
            webSettings.javaScriptEnabled = true
            itemView.youtube_view.loadData(frameVideo, "text/html", "utf-8")

            /*itemView.play_youtube.setOnClickListener {
                itemView.youtube_view.performClick()
            }*/



        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val item  =  LayoutInflater.from(con).inflate(R.layout.youtube_item,parent,false)

        return MYViewHolder(item)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val book = liste[position]
        holder.set(book)



    }

    fun getHTML(videoId: String): String? {
        return """<iframe class="youtube-player" style="border: 0; width: 100%; height: 96%;padding:0px; margin:0px" id="ytplayer" type="text/html" src="http://www.youtube.com/embed/$videoId?&theme=dark&autohide=2&modestbranding=1&showinfo=0&autoplay=1s=0" frameborder="0" allowfullscreen autobuffer controls onclick="this.play()">
</iframe>
"""
    }



}