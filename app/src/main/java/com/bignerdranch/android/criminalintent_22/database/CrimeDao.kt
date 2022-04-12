package com.bignerdranch.android.criminalintent_22.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.criminalintent_22.Crime
import java.util.*

@Dao
interface CrimeDao {

    @Insert
    fun addCrime(crime: Crime)

    @Update
    fun updateCrime(crime : Crime)

    @Query("SELECT * FROM crime")
    fun getCrimes() : LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID) : LiveData<Crime?>
}