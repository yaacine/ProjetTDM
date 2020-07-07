package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Video
import com.example.projettdm.DataManager.Entities.VideoYoutube

@Dao
interface VideoDAO {
    @Query("SELECT * FROM video")
    fun getAll(): List<Video>

    @Query("SELECT * FROM video WHERE idCountry LIKE :id")
    fun findBycountry(id: Int):  List<Video>


    @Insert
    fun insertAll(vararg video: Video)

    @Delete
    fun delete(video: Video)

    @Update
    fun updateCountry(vararg video: Video)

    @Insert
    fun insertAllYoutube(vararg video : VideoYoutube)

    @Query("select url from video_youtube where idCountry=:id")
    fun get_youtube_id(id:Int):List<String>
}