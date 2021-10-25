package com.airlineassignment.data.repo

import androidx.lifecycle.LiveData
import com.airlineassignment.data.local.AppDatabase
import com.airlineassignment.data.local.RosterEntity
import com.airlineassignment.data.remote.RemoteDataAPI
import com.airlineassignment.data.remote.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RosterRepository(private val database: AppDatabase) {

    fun rosters(): LiveData<List<RosterEntity>> = database.rosterDao.getRoster()

    suspend fun refreshRoster() {
        withContext(Dispatchers.IO) {
            val rosterList = RemoteDataAPI.remoteDataService.getRoster()
            database.rosterDao.insertRoster(*rosterList.asDatabaseModel())
        }
    }
}