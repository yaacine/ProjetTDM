package com.example.projettdm.DataManager.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country (

    @ColumnInfo(name = "countryId")
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "code") var code: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "hymeSrc") var hymeSrc: String,
    @ColumnInfo(name = "flagSrc") var flagSrc: String,
    @ColumnInfo(name = "visited") var visted: Boolean,
    @ColumnInfo(name = "favorite") var favorite: Boolean

)