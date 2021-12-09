package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.SearchTeamSingle

data class SearchTeams(
    val teams: List<SearchTeamSingle>? = listOf()
)