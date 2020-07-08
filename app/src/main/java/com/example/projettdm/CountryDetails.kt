package com.example.projettdm


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_country_details.*


class CountryDetails : AppCompatActivity() {
    var navigationView: BottomNavigationView? = null
    var country_id :Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        navigationView =findViewById(R.id.bottom_navigation);

        //var i :Intent =getIntent()
        this.country_id = intent.getStringExtra("countryId")?.toInt()!!

        title=resources.getString(R.string.information)
        loadFragment(InformationFragment())

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home-> {
                    title=resources.getString(R.string.information)
                    loadFragment(InformationFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_twitter-> {
                    title=resources.getString(R.string.tweets)
                    loadFragment(TweetsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_videos-> {
                    title=resources.getString(R.string.videos)
                    loadFragment(VideosFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }

    }

    // updated load fragment to pass id to fragments
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val bundle = Bundle()
        bundle.putInt("country_id",this.country_id!!) // country is an attribute

        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle // arguments passed
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
