package com.example.projettdm.DataManager.Entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "personnality",
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = ["countryId"],
        childColumns = ["idCountry"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["idCountry"])]
)
data class Personnality (

    @ColumnInfo(name = "personnalityId")
    @PrimaryKey(autoGenerate = true) var personnalityId: Int,

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String,
    // image of the personnality
    @ColumnInfo(name = "resourceId") var resourceId: String,
    // link to the wekipedia article about that person
    @ColumnInfo(name = "link") var link: String,

    @ColumnInfo(name = "idCountry") var idCountry:  Int


){
    @Ignore
    constructor(name: String, description: String , resourceId: String, link: String, idCountry: Int) :
            this(0, name, description, resourceId, link,idCountry)
}