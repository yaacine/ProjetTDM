package com.example.projettdm.DataManager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projettdm.DataManager.Dao.*
import com.example.projettdm.DataManager.Entities.*


@Database(
    entities = [Country::class, Video::class , Image::class , Tweet::class , Personnality::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun CountryDao(): CountryDAO
    abstract fun VideoDao(): VideoDAO
    abstract fun ImageDao(): ImageDAO
    abstract fun TweetDao(): TweetDAO
    abstract fun PersonnalityDAO(): PersonnalityDAO


    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                AppDatabase::class.java, "geomob.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
