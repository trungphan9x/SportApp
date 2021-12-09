package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.Event
import us.reachmobi.sportapp.data.model.SearchEventSingle

data class SearchEvents(
    val event: List<Event>? = listOf()
)