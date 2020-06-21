package com.example.projettdm.DataManager.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "image",
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = ["countryId"],
        childColumns = ["idCountry"],
        onDelete = CASCADE
    )]
)
data class Image (

    @ColumnInfo(name = "imageId")
    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "resourceId") var resourceId: String,
    @ColumnInfo(name = "owner") var owner: String,

    @ColumnInfo(name = "idCountry") var idCountry:  Int


)