package com.example.projettdm

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Dao.CountryDAO
import com.example.projettdm.DataManager.Dao.ImageDAO
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.android.synthetic.main.fragment_information.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InformationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var country_id:Int = 1
    private var country: Country? = null
    private var ImagesList : MutableList<Int> = mutableListOf()

    private var databse: AppDatabase? = null
    private var country_dao : CountryDAO ? = null
    private var image_dao : ImageDAO ? = null

    private lateinit var mediaPlayer: MediaPlayer
    private var started: Boolean = false
    private var playing: Boolean = false
    private var point:Int = 0





    private var listener: OnFragmentInteractionListener? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

       // adapter = context?.let { SlideShowAdapter(it,a) }
        //slider.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
                active.image_dao = active.databse?.ImageDao()
                if(active.country_id>0){

                    active.country = active.country_dao?.findById(active.country_id)

                    active.country?.visited = true
                    active.country?.let {
                        active.country_dao?.updateCountry(
                            it
                        )
                    }

                    active.image_dao?.findBycountry(active.country_id)?.forEach { i->
                        active.ImagesList.add(i.resourceId)
                    }

                }
                else{
                    Log.e(" country_id "," not available during fetch from db ")
                }


                return null
            }

            override fun onPostExecute(result: Void?) {

                mediaPlayer = MediaPlayer.create(context, country?.hymeSrc!!)
                    txt_description.text = country?.description
                    txt_histoire.text = country?.history
                    txt_nom.text = country?.name
                    txt_population.text = country?.population.toString()
                    txt_surface.text = country?.surface.toString()
                    img_flag.setImageResource(country?.flagSrc!!)


                var ls:MutableList<String> = mutableListOf()
                ImagesList.forEach { i->
                    ls.add(uri_from_ressource(i).toString())
                }

                //val uri = uri_from_ressource(R.drawable.dz_2)
               // val uri1 = uri_from_ressource(R.drawable.dz_3)

                //var ls = arrayOf(uri.toString(),uri1.toString())
                slider.setItems(ls.toList())
                slider.getIndicator()
            }

        }
        luncher.execute()


        return inflater.inflate(R.layout.fragment_information, container, false)

    }

     /*override fun onSaveInstanceState(outState: Bundle) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState)
        // Save our own state now
        outState.putSerializable("courtry", this.ImagesList)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("countryId",country?.countryId.toString())
            context?.startActivity(intent)
        }

        btn_play.setOnClickListener{

            if(!started){
                //mediaPlayer = MediaPlayer.create(context, R.raw.hyme_algeria)
                mediaPlayer.isLooping = true
                mediaPlayer.start()
                started = true
                playing = true
                btn_play.text = " Pause "
            }
            else{
                if(playing){
                    mediaPlayer.pause();
                   point = mediaPlayer.currentPosition;
                    btn_play.text =" Reprendre "
                }
                else{
                    mediaPlayer.seekTo(point);
                    mediaPlayer.start();
                    btn_play.text = " Suspendre "
                }
                playing = !playing
            }

        }


    }




    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

  /*  override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

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
         * @return A new instance of fragment InformationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun  uri_from_ressource(i:Int):Uri{
        val uri1 = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(i))
            .appendPath(resources.getResourceTypeName(i))
            .appendPath(resources.getResourceEntryName(i))
            .build()
        return uri1
    }

}


