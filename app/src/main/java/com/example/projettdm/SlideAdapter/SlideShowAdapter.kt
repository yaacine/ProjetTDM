package com.example.projettdm.SlideAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.projettdm.R
import com.example.projettdm.R.*
import kotlinx.android.synthetic.main.fragment_information.view.*


class SlideShowAdapter(var context: Context) : PagerAdapter() {
    var images:Array<Int> = arrayOf(drawable.dz_1, drawable.dz_3, drawable.dz_4)
    lateinit var inflater: LayoutInflater


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view== `object` as LinearLayout
    }

    override fun getCount(): Int {
        return images.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var image:ImageView


        Log.d("lunch_____",position.toString())
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view:View = inflater.inflate(id.slider,container,false) as View

        image = view.findViewById(id.slider)
        image.setBackgroundResource(images[position])
        container.addView(view)
        return view


    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView( `object` as LinearLayout)
    }
}