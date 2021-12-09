package us.reachmobi.sportapp.data.repository.remote

import android.app.usage.EventStats
import us.reachmobi.sportapp.util.Resource
import us.reachmobi.sportapp.base.BaseRepository
import us.reachmobi.sportapp.data.remote.api.SportApiService
import us.reachmobi.sportapp.data.remote.response.*


class RemoteRepositoryImpl(private val sportApiService: SportApiService) : BaseRepository(), RemoteRepository {
    override suspend fun getMyLeagues(): Resource<Leagues> {
        return safeApi {
            sportApiService.getMyLeagues()
        }
    }

    override suspend fun getLeagueDetail(leagueId: String): Resource<LeagueDetail> {
        return safeApi {
            sportApiService.getLeagueDetail(leagueId)
        }
    }

    override suspend fun getNextMatchByLeagueId(leagueId: String): Resource<Events> {
        return safeApi {
            sportApiService.getNextMatchByLeagueId(leagueId)
        }
    }

    override suspend fun getLastMatchByLeagueId(leagueId: String): Resource<Events> {
        return safeApi {
            sportApiService.getLastMatchByLeagueId(leagueId)
        }
    }

    override suspend fun getTeamDetailByTeamId(leagueId: String): Resource<Teams> {
        return safeApi {
            sportApiService.getTeamDetailByTeamId(leagueId)
        }
    }

    override suspend fun getTeamListByLeagueId(leagueId: String): Resource<Teams> {
        return safeApi {
            sportApiService.getTeamListByLeagueId(leagueId)
        }
    }

    override suspend fun getPlayerList(teamId: String): Resource<Players> {
        return safeApi {
            sportApiService.getPlayerList(teamId)
        }
    }

    override suspend fun searchMatches(query: String): Resource<SearchEvents> {
        return safeApi {
            sportApiService.searchMatches(query)
        }
    }

    override suspend fun searchTeams(query: String): Resource<Teams> {
        return safeApi {
            sportApiService.searchTeams(query)
        }
    }
}