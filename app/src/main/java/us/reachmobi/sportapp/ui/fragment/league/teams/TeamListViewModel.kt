package us.reachmobi.sportapp.ui.fragment.league.teams

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.enums.Status
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.ui.fragment.league.teams.TeamListFragment.Companion.UPDATE_RV_TEAMS
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

class TeamListViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {
    val leagueId = ObservableField<String>()

    fun getTeamListByLeagueId() {
        viewModelScope.launch {
            leagueId.get()?.let {
                remoteRepository.getTeamListByLeagueId(it).let {
                    when (it.status) {
                        Status.SUCCESS -> _uiEvent.postValue(UIEvent(UPDATE_RV_TEAMS, it.data))
                        Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                    }
                }
            }
        }

    }

    fun getTeamListWithSearch(keyword: String) {
        viewModelScope.launch {

            remoteRepository.searchTeams(keyword).let {
                when (it.status) {
                    Status.SUCCESS -> _uiEvent.postValue(UIEvent(UPDATE_RV_TEAMS, it.data))
                    Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                }
            }
        }
    }
}