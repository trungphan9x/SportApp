package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.League

data class Leagues(
    val leagues: List<League>? = listOf()
)