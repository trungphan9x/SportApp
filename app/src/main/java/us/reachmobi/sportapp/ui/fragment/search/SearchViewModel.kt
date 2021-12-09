package us.reachmobi.sportapp.ui.fragment.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.enums.Status
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.ui.fragment.league.match.MatchListFragment
import us.reachmobi.sportapp.ui.fragment.search.SearchFragment.Companion.UPDATE_RV_TEAMS_SEARCH
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

class SearchViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {

    fun getMatchesWithSearch(keyword: String) {
        viewModelScope.launch {
            loading.set(true)
            remoteRepository.searchMatches(keyword).let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.event?.forEach { event ->
                            remoteRepository.getTeamDetailByTeamId(event.idHomeTeam).let {
                                event.strHomeTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }

                            remoteRepository.getTeamDetailByTeamId(event.idAwayTeam).let {
                                event.strAwayTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }
                        }
                        _uiEvent.postValue(UIEvent(MatchListFragment.UPDATE_RV_MATCHES, it.data))
                        loading.set(false)
                    }
                    Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                }
            }
        }
    }

    fun getTeamListWithSearch(keyword: String) {
        viewModelScope.launch {
            loading.set(true)
            remoteRepository.searchTeams(keyword).let {
                when (it.status) {
                    Status.SUCCESS -> _uiEvent.postValue(UIEvent(UPDATE_RV_TEAMS_SEARCH,
                        it.data))
                    Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                }
                loading.set(false)
            }
        }
    }
}