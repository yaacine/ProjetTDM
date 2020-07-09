package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country


@Dao
interface CountryDAO {


    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM country WHERE favorite = 1")
    fun getAllFavorites(): List<Country>

    @Query("SELECT * FROM country WHERE visited = 1")
    fun getAllVisited(): List<Country>

    @Query("select * from country Where visited = 0 limit 1")
    fun getNewCountry(): List<Country>

    @Query("SELECT * FROM country WHERE code LIKE :code")
    fun findBycode(code: String): Country

    @Query("select * from country where countryId=:id")
    fun findById(id:Int): Country


    @Insert
    fun insertAll(vararg country: Country)

    @Delete
    fun delete(country: Country)


    @Update
    fun updateCountry(vararg country: Country)
}