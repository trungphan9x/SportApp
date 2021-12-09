package us.reachmobi.sportapp.data.repository.remote

import us.reachmobi.sportapp.data.remote.response.*
import us.reachmobi.sportapp.util.Resource


interface RemoteRepository {

    suspend fun getMyLeagues() : Resource<Leagues>

    suspend fun getLeagueDetail(leagueId: String): Resource<LeagueDetail>

    suspend fun getNextMatchByLeagueId(leagueId: String): Resource<Events>

    suspend fun getLastMatchByLeagueId(leagueId: String): Resource<Events>

    suspend fun getTeamDetailByTeamId(leagueId: String): Resource<Teams>

    suspend fun getTeamListByLeagueId(leagueId: String): Resource<Teams>

    suspend fun getPlayerList(teamId: String): Resource<Players>

    suspend fun searchMatches(query: String): Resource<SearchEvents>

    suspend fun searchTeams(query: String): Resource<Teams>

}