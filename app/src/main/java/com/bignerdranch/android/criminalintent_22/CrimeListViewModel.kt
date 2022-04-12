package com.bignerdranch.android.criminalintent_22

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CrimeListViewModel : ViewModel() {

    /*val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100){
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes.add(crime)
        }
    }*/

    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData: LiveData<List<Crime>> = crimeRepository.getCrimes()

    fun addCrime(crime: Crime){
        crimeRepository.addCrime(crime)
    }
}