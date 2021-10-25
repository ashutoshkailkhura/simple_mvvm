package com.airlineassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Roster(
    var aircraftType: String?,
    var captain: String?,
    var date: String?,
    var departure: String?,
    var destination: String?,
    var dutyCode: String?,
    var dutyID: String?,
    var firstOfficer: String?,
    var flightAttendant: String?,
    var flightnr: String?,
    var tail: String?,
    var timeArrive: String?,
    var timeDepart: String?
) : Parcelable {
    override fun toString(): String {
        return "aircraftType: $aircraftType,\n" +
                "captain: $captain,\n" +
                "date: $date,\n" +
                "departure: $departure,\n" +
                "destination: $destination,\n" +
                "dutyCode: $dutyCode,\n" +
                "dutyID: $dutyID,\n" +
                "firstOfficer: $firstOfficer,\n" +
                "flightAttendant: $flightAttendant,\n" +
                "flightnr: $flightnr,\n" +
                "tail: $tail,\n" +
                "timeArrive: $timeArrive,\n" +
                " timeDepart: $timeDepart"
    }
}

