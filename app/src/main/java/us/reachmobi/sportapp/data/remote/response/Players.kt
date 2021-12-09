package us.reachmobi.sportapp.data.remote.response

import us.reachmobi.sportapp.data.model.Player

data class Players(
    val player: List<Player>? = listOf()
)