package us.reachmobi.sportapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String? = "",
    val imageUrl: String? = "",
    val strLeague: String? = "",
    val strLeagueAlternate: String? = "",
    val strSport: String? = ""
) : Parcelable