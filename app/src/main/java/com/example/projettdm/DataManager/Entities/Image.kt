package com.example.projettdm.DataManager.Entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "image",
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = ["countryId"],
        childColumns = ["idCountry"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["idCountry"])]
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