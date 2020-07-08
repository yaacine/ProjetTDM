package com.example.projettdm

import android.annotation.SuppressLint
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import twitter4j.*

import twitter4j.conf.ConfigurationBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TweetsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TweetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TweetsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var tweetsAdapter: TweetsListAdapter
    private var country_id:Int = 0
    private var country: Country? = null
    private var countryName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments!!.getInt("country_id").let {it->
            Log.d(" Country_id_fount",it.toString())
            this.country_id = it

        }
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_tweets, container, false)
        Toast.makeText(activity, "Error loading the data", Toast.LENGTH_SHORT).show()
        tweetsAdapter = TweetsListAdapter( activity!!, R.layout.row_tweet, DataHolder.tweetsList)

        var listV : ListView = root!!.findViewById(R.id.tweetsList)
        listV.adapter = tweetsAdapter
        val active = this ;
        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                //in background
                //active

                if(active.country_id>0){

                    active.country = DataHolder.dbReference.CountryDao()?.findById(active.country_id)

                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                println("got country ====>"+active.country?.name)
                val cb = ConfigurationBuilder()
                cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("BiTL2ru2EuVMElv7WNU4EJEuf")
                    .setOAuthConsumerSecret("N4muFKXI0gYJYOvAaLFAClQyf4MX8W7979zNCuAXf3AI2WqbQV")
                    .setOAuthAccessToken("1059903815921623041-o61IrmoeCRyhobGB9I8DA0RrmaU7ED")
                    .setOAuthAccessTokenSecret("4UagcnwlsZkEzfyxfGF0MURtyu8x5OEA87ZVRnI7IJ7Ff")
                val tf = TwitterFactory(cb.build())
                val twitter: Twitter = tf.getInstance()
                try {
                    val query = Query(active.country?.name+"News")
                    lateinit var result: QueryResult
                    GlobalScope.launch {
                        Log.d("getting tweets","tweets ")
                        result = twitter.search(query)
                    }.invokeOnCompletion {

                        val tweets: List<twitter4j.Status> = result.getTweets()
                        for (tweet in tweets) {
                            DataHolder.tweetsList.add(tweet)
                            println(
                                "@" + tweet.getUser().getScreenName().toString() + " - " + tweet.getText() +"==>"
                            )
                        }


                        // adapter.notifyDataSetChanged()

                        activity!!.runOnUiThread(java.lang.Runnable {

                            active.tweetsAdapter.notifyDataSetChanged()
                            // adapter.notifyDataSetChanged()

                        })

                    }


                } catch (te: TwitterException) {
                    te.printStackTrace()
                    System.out.println("Failed to search tweets: " + te.errorMessage)
                    System.exit(-1)
                }

            }

        }
        luncher.execute()


        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
         * @return A new instance of fragment TweetsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TweetsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
