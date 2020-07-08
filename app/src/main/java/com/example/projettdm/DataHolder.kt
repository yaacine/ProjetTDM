package com.example.projettdm

import android.util.Log
import com.example.projettdm.DataManager.AppDatabase
import com.example.projettdm.DataManager.Entities.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import twitter4j.Status
import java.io.BufferedReader
import java.io.File


class  DataHolder {

    companion object{
        lateinit var dbReference:AppDatabase
        var countriesList = arrayListOf<Country>()
        var tweetsList = arrayListOf<Status>()


        fun getAll() {
            GlobalScope.launch {

                var myDataList = dbReference.CountryDao().getAll()
                countriesList.addAll(myDataList)
            }

        }

    }




}


