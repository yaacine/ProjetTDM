package com.example.projettdm

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import twitter4j.Status

class TweetsListAdapter(
    var mCtx: Context, var resource: Int,
    var items: MutableList<Status>
) : ArrayAdapter<Status>(mCtx, resource, items as MutableList<Status>) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val imageView: ImageView = view.findViewById(R.id.tweetIcon)
        var name: TextView = view.findViewById(R.id.tweetterUser)
        var description: TextView = view.findViewById(R.id.tweetContent)

        var openTwitterBtn: TextView = view.findViewById(R.id.buttonTwitter)

        var status: Status? = items[position]


        val uri = R.drawable.ic_iconfinder_icon_white
        val myres: Drawable = mCtx.resources.getDrawable(uri)
        imageView.setColorFilter(R.color.colorPrimary)
        imageView.setImageDrawable(myres)

        //       imageView.setImageDrawable(mCtx.resources.getDrawable(1,null))
        name.text = status?.user?.name.toString()
        description.text = status?.text.toString()


        println("ayizem")

        openTwitterBtn.setOnClickListener {
            println("we are deleting some stuff")



        }

        return view
    }

}

