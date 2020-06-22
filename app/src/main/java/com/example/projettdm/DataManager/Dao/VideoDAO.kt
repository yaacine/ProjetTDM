package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Video

@Dao
interface VideoDAO {
    @Query("SELECT * FROM video")
    fun getAll(): List<Video>

    @Query("SELECT * FROM video WHERE idCountry LIKE :id")
    fun findBycountry(id: String):  List<Video>


    @Insert
    fun insertAll(vararg video: Video)

    @Delete
    fun delete(video: Video)

    @Update
    fun updateCountry(vararg video: Video)
}