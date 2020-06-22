package com.example.projettdm.DataManager.Dao

import androidx.room.*
import com.example.projettdm.DataManager.Entities.Country


@Dao
interface CountryDAO {


    @Query("SELECT * FROM country")
    fun getAll(): List<Country>

    @Query ("SELECT countryId, name , code, visited, favorite FROM country")
    fun getAllAbstracts(): List<Country>


    @Query ("SELECT countryId, name ,code, visited, favorite FROM country WHERE favorite=1  ")
    fun getFavoriteAbstracts(): List<Country>


    @Query("SELECT * FROM country WHERE code LIKE :code")
    fun findBycode(code: String): Country

    @Query("SELECT * FROM country WHERE countryId LIKE :countryId")
    fun findByid(countryId: String): Country

    @Insert
    fun insertAll(vararg country: Country)

    @Delete
    fun delete(country: Country)

    @Update
    fun updateCountry(vararg country: Country)
}