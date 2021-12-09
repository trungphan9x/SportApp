package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.Event

data class Events(
    val events: List<Event>? = listOf()
)