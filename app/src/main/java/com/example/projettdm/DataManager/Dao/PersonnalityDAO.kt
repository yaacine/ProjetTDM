package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country
import com.example.projettdm.DataManager.Entities.Image
import com.example.projettdm.DataManager.Entities.Personnality
import com.example.projettdm.DataManager.Entities.Video

@Dao
interface PersonnalityDAO {
    @Query("SELECT * FROM personnality")
    fun getAll(): List<Personnality>

    @Query("SELECT * FROM personnality WHERE idCountry LIKE :countryId")
    fun findBycountry(countryId: String):  List<Personnality>

    @Insert
    fun insertAll(vararg person: Personnality)

    @Delete
    fun delete(person: Video)

    @Update
    fun updateCountry(vararg person:  Personnality)
}