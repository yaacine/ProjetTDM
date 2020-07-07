package com.example.projettdm.DataManager.Entities


import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "video_youtube",
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = ["countryId"],
        childColumns = ["idCountry"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["idCountry"])]
)
data class VideoYoutube (

    @ColumnInfo(name = "videoId")
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "idCountry") var idCountry:  Int


){
    @Ignore
    constructor( url: String, idCountry: Int) :
            this(0,url,idCountry)
}