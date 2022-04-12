package com.bignerdranch.android.criminalintent_22

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bignerdranch.android.criminalintent_22.database.CrimeDatabase
import com.bignerdranch.android.criminalintent_22.database.migration_1_2
import java.io.File
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    companion object{
        private var instance: CrimeRepository? = null

        fun initialize(context: Context){
            if(instance == null){
                instance = CrimeRepository(context)
            }
        }

        fun get() : CrimeRepository{
            return instance ?: throw IllegalStateException("CrimeRepository must be initalized")
        }
    }

    private val database : CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2).build()

    private val crimeDao = database.crimeDao()

    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    fun addCrime(crime: Crime){
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    fun updateCrime(crime: Crime){
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun getPhotoFile(crime: Crime) : File = File(filesDir, crime.photoFileName)
}