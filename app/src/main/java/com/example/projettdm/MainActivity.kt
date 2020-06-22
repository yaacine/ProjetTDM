package com.example.projettdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {

            val intent = Intent(this, CountryDetails::class.java)
            startActivity(intent)
        }
    }
}
