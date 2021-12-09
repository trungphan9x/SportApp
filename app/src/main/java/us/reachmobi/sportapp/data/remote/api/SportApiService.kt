package us.reachmobi.sportapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import us.reachmobi.sportapp.BuildConfig
import us.reachmobi.sportapp.data.remote.response.*

interface SportApiService {
    @GET("https://trungphan9x.github.io/my_leagues.json")
    suspend fun getMyLeagues(): Leagues

//    @GET("error")
//    suspend fun getMyLeagues(): Leagues

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/lookupleague.php")
    suspend fun getLeagueDetail(@Query("id") leagueId: String): LeagueDetail

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/eventsnextleague.php")
    suspend fun getNextMatchByLeagueId(@Query("id") leagueId: String): Events

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/eventspastleague.php")
    suspend fun getLastMatchByLeagueId(@Query("id") leagueId: String): Events

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/lookupteam.php")
    suspend fun getTeamDetailByTeamId(@Query("id") teamId: String): Teams

    @GET("api/v1/json/${BuildConfig.ACCESS_TOKEN}/lookup_all_teams.php")
    suspend fun getTeamListByLeagueId(@Query("id") leagueId: String): Teams

    @GET("api/v1/json/${BuildConfig.ACCESS_TOKEN}/lookup_all_players.php")
    suspend fun getPlayerList(@Query("id") teamId: String): Players

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/searchevents.php")
    suspend fun searchMatches(@Query("e") query: String): SearchEvents

    @GET("/api/v1/json/${BuildConfig.ACCESS_TOKEN}/searchteams.php")
    suspend fun searchTeams(@Query("t") query: String): Teams
}