package com.example.projettdm

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CountryListAdapter(
    var mCtx: Context, var resource: Int,
    var items: List<Country>?
) : ArrayAdapter<Country>(mCtx, resource, items as MutableList<Country>) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val imageView: ImageView = view.findViewById(R.id.flagList)
        var name: TextView = view.findViewById(R.id.titleIntervention)
        var description: TextView = view.findViewById(R.id.plombierIntervention)
        var deleteBtn: TextView = view.findViewById(R.id.deleteBtn)
        var editBtn: TextView = view.findViewById(R.id.editBtn)

        var country: Country? = items?.get(position)


        val uri = country?.flagSrc
        val myres: Drawable = mCtx.getResources().getDrawable(uri!!)
        imageView.setImageDrawable(myres)

        //       imageView.setImageDrawable(mCtx.resources.getDrawable(1,null))
        name.text = country?.name + "  |  " + country?.code ?.toUpperCase()
        description.text = country?.description.toString()

        deleteBtn.setOnClickListener {
            println("we are deleting some stuff")
            //DataManager.countrysList.remove(items?.get(position))


            GlobalScope.launch {

                var myDataList =
                    items?.get(position)?.let { it1 ->
                        DataHolder.dbReference.CountryDao().delete(
                            it1
                        )
                    }
/*
                var myDataNewList =  DataManager.dbReference.interventionDao().getAll()
                DataManager.interventionsList.clear()
                println("deleted ====>"+ myDataNewList.size)
                DataManager.interventionsList.addAll(myDataNewList)
*/
            }.invokeOnCompletion {

                var activity = (context as MainActivity)
                activity.runOnUiThread(java.lang.Runnable {

                    activity.adapter.notifyDataSetChanged()


                })
            }


        }


        // Set a click listener for button widget
        editBtn.setOnClickListener {

            /*
        }
            // Initialize a new layout inflater instance
            val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.popup,null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // If API level 23 or higher then execute the code
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }

            // Get the widgets reference from custom view
            val nomPlombierEdit = view.findViewById<TextView>(R.id.nomPlombierEdit)
            val dateEdit = view.findViewById<TextView>(R.id.dateEdit)
            val typeEdit = view.findViewById<TextView>(R.id.typeEdit)
            val submitEdit = view.findViewById<Button>(R.id.submitEdit)



            // Set a click listener for popup's button widget
            submitEdit.setOnClickListener{
                // Dismiss the popup window
                popupWindow.dismiss()
            }




            // Finally, show the popup window on app
            val parent: View = R.layout.activity_main  as View
            TransitionManager.beginDelayedTransition(parent as ViewGroup?)
            popupWindow.showAtLocation(
                parent, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )*/
        }

        return view
    }

}