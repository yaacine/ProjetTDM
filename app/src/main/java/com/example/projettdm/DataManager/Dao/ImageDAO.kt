package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Image
import com.example.projettdm.DataManager.Entities.Video

@Dao
interface ImageDAO {
    @Query("SELECT * FROM image")
    fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE idCountry LIKE :countryId")
    fun findBycountry(countryId: Int):  List<Image>

    @Insert
    fun insertAll(vararg image: Image)

    @Delete
    fun delete(video: Video)

    @Update
    fun updateCountry(vararg image:  Image)
}