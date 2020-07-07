package com.example.projettdm

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import twitter4j.*

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Toast.makeText(activity, "Error loading the data", Toast.LENGTH_SHORT).show()

        val cb = ConfigurationBuilder()
             cb.setDebugEnabled(true)
            .setOAuthConsumerKey("RvXutluA3C8wwS3LpEJZ16rS3")
            .setOAuthConsumerSecret("NZcPWA3nXq2mFGOQg8CqG4mP3KLYTgMSx2eeNBK9AYa7TUUTVL")
            .setOAuthAccessToken("1223554396883124224-kpX9Av4WidSXK2aaHHZXAJBfm8bXLn")
            .setOAuthAccessTokenSecret("iciEbhP6DH6BKJnPg71VP6uMAD5iifNM2Ka0fGsRlQ4Jt")
        val tf = TwitterFactory(cb.build())
        val twitter: Twitter = tf.getInstance()
        try {
            val query = Query("Algeria")
            val result: QueryResult
            result = twitter.search(query)
            val tweets: List<Status> = result.getTweets()
            for (tweet in tweets) {
                println(
                    "@" + tweet.getUser().getScreenName().toString() + " - " + tweet.getText() +"==>"
                )
            }
            System.exit(0)
        } catch (te: TwitterException) {
            te.printStackTrace()
            System.out.println("Failed to search tweets: " + te.errorMessage)
            System.exit(-1)
        }
        return inflater.inflate(R.layout.fragment_tweets, container, false)
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
    }
    */


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
