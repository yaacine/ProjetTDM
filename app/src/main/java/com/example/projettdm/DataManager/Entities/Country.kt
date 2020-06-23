package com.example.projettdm.DataManager.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country (
    @ColumnInfo(name = "countryId")
    @PrimaryKey(autoGenerate = true) var countryId: Int ,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "code") var code: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "history") var history: String,
    @ColumnInfo(name = "hymeSrc") var hymeSrc: String,
    @ColumnInfo(name = "flagSrc") var flagSrc: String,
    @ColumnInfo(name = "visited") var visited: Boolean,
    @ColumnInfo(name = "favorite") var favorite: Boolean


){
    @Ignore
    constructor(name: String,code: String, history: String, hymeSrc: String,flagSrc: String,visited: Boolean,favorite: Boolean, description: String ) :
            this(0, name, code, history, description, hymeSrc, flagSrc,visited, favorite)
}