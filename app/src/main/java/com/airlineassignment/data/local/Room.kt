package com.airlineassignment.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RosterDao {
    @Query("select * from roster")
    fun getRoster(): LiveData<List<RosterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoster(vararg rosterData: RosterEntity)
}

@Database(
    entities = [RosterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val rosterDao: RosterDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "roster.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
