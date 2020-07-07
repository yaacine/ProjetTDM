package com.example.projettdm.Adapters


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.DataManager.Entities.Video
import com.example.projettdm.R
import kotlinx.android.synthetic.main.fragment_information.view.*
import kotlinx.android.synthetic.main.video_item.view.*


class VideoAdapter(ls: MutableList<Video>, cntx:Context) : RecyclerView.Adapter<VideoAdapter.MYViewHolder>() {
    var liste: MutableList<Video> = ls
    var con : Context = cntx


    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun set(book:Video){

           // itemView.detail_btn.setOnClickListener {}
            /*
            val videoPath = "android.resource://$con.packageName/$book"
            val uri: Uri = Uri.parse(videoPath)
            itemView.video_view.setVideoURI(uri)
            val mediaController = MediaController(con)
            itemView.video_view.setMediaController(mediaController)
            mediaController.setAnchorView(itemView.video_view)
            mediaController.show() */

            val videoPath = "android.resource://$con.packageName/$book"
            val uri = uri_from_ressource(book.resourceId)

            itemView.title_vid.text = book.title
            itemView.video_view.setVideoURI(uri)
            itemView.video_view.seekTo(1)

            itemView.play_vid.setOnClickListener {
                val mediaController = MediaController(con)
                itemView.video_view.setMediaController(mediaController)
                mediaController.setAnchorView(itemView.video_view)
                mediaController.show()
                itemView.video_view.start()
            }
        }
    }

    fun  uri_from_ressource(i:Int): Uri {


        val uri1 = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(con.resources.getResourcePackageName(i))
            .appendPath(con.resources.getResourceTypeName(i))
            .appendPath(con.resources.getResourceEntryName(i))
            .build()
        return uri1
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val item  =  LayoutInflater.from(con).inflate(R.layout.video_item,parent,false)

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