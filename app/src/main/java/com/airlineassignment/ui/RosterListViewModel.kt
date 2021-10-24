package com.airlineassignment.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.airlineassignment.data.local.getDatabase
import com.airlineassignment.data.repo.RosterRepository
import kotlinx.coroutines.launch

class RosterListViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val rosterRepository = RosterRepository(database)

    val rosterList = rosterRepository.rosters()

    fun getRosterList() {
        viewModelScope.launch {
            try {
                rosterRepository.refreshRoster()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    class Factory(private val activity: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RosterListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return RosterListViewModel(activity) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}