package com.example.projettdm.DataManager.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country (

    @ColumnInfo(name = "countryId" ,index = true)
    @PrimaryKey(autoGenerate = true) var countryId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "code") var code: String,
    @Ignore @ColumnInfo(name = "description") var description: String,
    @Ignore @ColumnInfo(name = "hymeSrc") var hymeSrc: String,
    @Ignore @ColumnInfo(name = "flagSrc") var flagSrc: String,
    @ColumnInfo(name = "visited") var visited: Boolean,
    @ColumnInfo(name = "favorite") var favorite: Boolean

)