package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.LeagueDetailSingle

data class LeagueDetail(
    val leagues: List<LeagueDetailSingle>? = listOf()
)