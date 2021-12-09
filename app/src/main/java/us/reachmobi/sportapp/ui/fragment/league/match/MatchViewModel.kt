package us.reachmobi.sportapp.ui.fragment.league.match

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.enums.Status
import us.reachmobi.sportapp.data.remote.response.Events
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.ui.fragment.league.match.MatchListFragment.Companion.UPDATE_RV_MATCHES
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

class MatchViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {

    val _event: MutableLiveData<Events?> = MutableLiveData()
    val event: LiveData<Events?> get() = _event
    val leagueId = ObservableField<String>()
    val isLastMatch = ObservableBoolean(true)

    fun getLastMatchByLeagueId(matchId : String) {
        viewModelScope.launch {
            loading.set(true)
            remoteRepository.getLastMatchByLeagueId(matchId).let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.events?.forEach { event ->
                            remoteRepository.getTeamDetailByTeamId(event.idHomeTeam).let {
                                event.strHomeTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }

                            remoteRepository.getTeamDetailByTeamId(event.idAwayTeam).let {
                                event.strAwayTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }
                        }
                        _uiEvent.postValue(UIEvent(UPDATE_RV_MATCHES, it.data))
                        loading.set(false)
                    }
                    Status.ERROR -> _error.postValue(UIEvent(ERROR,it.message))
                }
            }
        }
    }

    fun getNextMatchByLeagueId(matchId : String) {
        viewModelScope.launch {
            loading.set(true)
            remoteRepository.getNextMatchByLeagueId(matchId).let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.events?.forEach { event ->
                            remoteRepository.getTeamDetailByTeamId(event.idHomeTeam).let {
                                event.strHomeTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }

                            remoteRepository.getTeamDetailByTeamId(event.idAwayTeam).let {
                                event.strAwayTeamBadge = it.data?.teams?.get(0)?.strTeamBadge ?: ""
                            }
                        }
                        _uiEvent.postValue(UIEvent(UPDATE_RV_MATCHES, it.data))
                        loading.set(false)
                    }
                    Status.ERROR -> _error.postValue(UIEvent(ERROR,it.message))
                }
            }
        }
    }

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
                        _uiEvent.postValue(UIEvent(UPDATE_RV_MATCHES, it.data))
                        loading.set(false)
                    }
                    Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                }
            }
        }
    }
}