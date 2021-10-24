package com.airlineassignment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.airlineassignment.model.Roster


@Entity(tableName = "roster")
data class RosterEntity(
    @ColumnInfo(name = "Aircraft Type")
    var aircraftType: String?,
    @ColumnInfo(name = "Captain")
    var captain: String?,
    @ColumnInfo(name = "Date")
    var date: String?,
    @ColumnInfo(name = "Departure")
    var departure: String?,
    @ColumnInfo(name = "Destination")
    var destination: String?,
    @ColumnInfo(name = "DutyCode")
    var dutyCode: String?,
    @ColumnInfo(name = "DutyID")
    var dutyID: String?,
    @ColumnInfo(name = "First Officer")
    var firstOfficer: String?,
    @ColumnInfo(name = "Flight Attendant")
    var flightAttendant: String?,
    @ColumnInfo(name = "Flightnr")
    var flightnr: String?,
    @ColumnInfo(name = "Tail")
    var tail: String?,
    @ColumnInfo(name = "Time_Arrive")
    var timeArrive: String?,
    @ColumnInfo(name = "Time_Depart")
    var timeDepart: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

fun List<RosterEntity>.asDomainModel(): List<Roster> {
    return map {
        Roster(
            aircraftType = it.aircraftType,
            captain = it.captain,
            date = it.date,
            departure = it.departure,
            destination = it.destination,
            dutyCode = it.dutyCode,
            dutyID = it.dutyID,
            firstOfficer = it.firstOfficer,
            flightAttendant = it.flightAttendant,
            flightnr = it.flightnr,
            tail = it.tail,
            timeArrive = it.timeArrive,
            timeDepart = it.timeDepart,
        )
    }
}