package com.example.projettdm.Adapters



import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.DataManager.Entities.Video
import com.example.projettdm.R
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.fragment_information.view.*
import kotlinx.android.synthetic.main.video_item.view.*
import kotlinx.android.synthetic.main.youtube_item.view.*


class YoutubeAdapter(ls: MutableList<String>, cntx:Context) : RecyclerView.Adapter<YoutubeAdapter.MYViewHolder>() {
    var liste: MutableList<String> = listOf<String>("XfP31eWXli4") as MutableList<String>
    var con : Context = cntx


    private var key = "AIzaSyAILXPCxjNLGaJAxtF4GTLWba321t3vnRU"




    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun set(book:String){

            var yt_listener = object : YouTubePlayer.OnInitializedListener{
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
            }



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



}