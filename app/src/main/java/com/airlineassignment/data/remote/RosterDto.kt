package com.airlineassignment.data.remote

import com.airlineassignment.data.local.RosterEntity
import com.google.gson.annotations.SerializedName

class RosterDto : ArrayList<RosterDto.RosterItemDto>() {
    data class RosterItemDto(
        @SerializedName("Aircraft Type")
        var aircraftType: String?,
        @SerializedName("Captain")
        var captain: String?,
        @SerializedName("Date")
        var date: String?,
        @SerializedName("Departure")
        var departure: String?,
        @SerializedName("Destination")
        var destination: String?,
        @SerializedName("DutyCode")
        var dutyCode: String?,
        @SerializedName("DutyID")
        var dutyID: String?,
        @SerializedName("First Officer")
        var firstOfficer: String?,
        @SerializedName("Flight Attendant")
        var flightAttendant: String?,
        @SerializedName("Flightnr")
        var flightnr: String?,
        @SerializedName("Tail")
        var tail: String?,
        @SerializedName("Time_Arrive")
        var timeArrive: String?,
        @SerializedName("Time_Depart")
        var timeDepart: String?
    )
}

/**
 * Convert Network results to database objects
 */
fun RosterDto.asDatabaseModel(): Array<RosterEntity> {
    return map {
        RosterEntity(
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
    }.toTypedArray()
}


/**
 * Convert Network results to domain objects
 */
//fun List<RosterDto>.asDomainModel(): List<Roster> {
//    return map {
//        Roster(
//
//        )
//    }
//}

