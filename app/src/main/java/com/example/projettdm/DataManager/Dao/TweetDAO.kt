package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Tweet
import com.example.projettdm.DataManager.Entities.Video

@Dao
interface TweetDAO {
    @Query("SELECT * FROM tweet")
    fun getAll(): List<Tweet>

    @Query("SELECT * FROM tweet WHERE idCountry LIKE :countryId")
    fun findBycountry(countryId: String):  List<Tweet>

    @Insert
    fun insertAll(vararg tweet: Tweet)

    @Delete
    fun delete(tweet: Tweet)

    @Update
    fun updateCountry(vararg tweet: Tweet)
}