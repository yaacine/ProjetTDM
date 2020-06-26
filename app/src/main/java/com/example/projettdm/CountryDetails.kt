package com.example.projettdm


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_country_details.*
import androidx.fragment.app.Fragment

class CountryDetails : AppCompatActivity() {
    var navigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        navigationView =findViewById(R.id.bottom_navigation);

        title=resources.getString(R.string.information)
        loadFragment(InformationFragment())

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_sms-> {
                    title=resources.getString(R.string.information)
                    loadFragment(InformationFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_home-> {
                    title=resources.getString(R.string.tweets)
                    loadFragment(TweetsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_notifications-> {
                    title=resources.getString(R.string.videos)
                    loadFragment(VideosFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }

    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
