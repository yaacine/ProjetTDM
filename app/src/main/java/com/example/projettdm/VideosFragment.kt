package com.example.projettdm


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tweets.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projettdm.Adapters.VideoAdapter
import com.example.projettdm.Adapters.YoutubeAdapter
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Dao.CountryDAO
import com.example.projettdm.DataManager.Dao.VideoDAO
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Video
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_videos.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VideosFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [VideosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var myContext: FragmentActivity? = null
    private var listener: OnFragmentInteractionListener? = null

    private var country_id:Int = 0
    private var country: Country? = null
    private var list_Video : MutableList<Video> = mutableListOf()
    private var youtube_list : MutableList<String> = mutableListOf()


    private var databse: AppDatabase? = null
    private var country_dao : CountryDAO? = null
    private var video_dao : VideoDAO? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ls = listOf<Int>(R.raw.algerie1,R.raw.algerie2)
        recycler_view.adapter = VideoAdapter(ls,context!!)
        recycler_view.layoutManager = LinearLayoutManager(context!!)
        recycler_view.setHasFixedSize(true)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        arguments!!.getInt("country_id").let {it->
            Log.d(" Country_id_fount",it.toString())
            this.country_id = it

        }
        val active = this ;
        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                //in background
                //active
                active.databse = AppDatabase.invoke(context!!)

                active.country_dao = active.databse?.CountryDao()
                active.video_dao = active.databse?.VideoDao()
                if(active.country_id>0){

                    active.country = active.country_dao?.findById(active.country_id)
                    active.video_dao?.findBycountry(active.country_id)?.forEach { i->
                        active.list_Video.add(i)
                    }
                    active.youtube_list  = active.video_dao?.get_youtube_id(active.country_id) as MutableList<String>

                }
                else{
                    Log.e(" country_id "," not available during fetch from db ")
                }


                return null
            }

            override fun onPostExecute(result: Void?) {

                txt_nom_vid.text = active.country?.name
            }

        }
        luncher.execute()


        val rootView: View =
            inflater.inflate(R.layout.fragment_videos, container, false)

        val recyclerView =
            rootView.findViewById<View>(R.id.recycler_view) as RecyclerView

        val lay_manager = LinearLayoutManager(activity)
        recyclerView.layoutManager = lay_manager
        val mAdapter = VideoAdapter(this.list_Video,context!!)
        recyclerView.adapter = mAdapter
        lay_manager.stackFromEnd = true
        recyclerView.itemAnimator = DefaultItemAnimator()


        val recycler2 = rootView.findViewById<View>(R.id.recycler_view_youtube) as RecyclerView
        val lay_manager2 = LinearLayoutManager(activity)
        recycler2.layoutManager = lay_manager2
        val mAdapter2 = YoutubeAdapter(this.youtube_list,context!!)
        recycler2.adapter = mAdapter2
        lay_manager2.stackFromEnd = true
        recycler2.itemAnimator = DefaultItemAnimator()




        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linear_holder.scrollTo(0,0)
        linear_holder.scrollY = 0
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = activity as FragmentActivity

    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VideosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context?.startActivity(intent)
        }
    }

}
