package com.example.projettdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = AppDatabase(this)


        GlobalScope.launch {
            db.CountryDao().insertAll(Country(name = "Algeria", code = "DZ" ,
                description = "flutter run " , visted = false, ))
            data = db.todoDao().getAll()

            data?.forEach {
                println(it)
            }
        }
        val iconName = resources.getResourceEntryName(R.drawable.ic_launcher_background)

        println("icon name ===>"+ iconName)
        button.setOnClickListener {

            val intent = Intent(this, CountryDetails::class.java)
            startActivity(intent)
        }
    }
}
