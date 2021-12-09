package us.reachmobi.sportapp.ui.fragment.league.info

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.enums.Status
import us.reachmobi.sportapp.data.local.CoverImage
import us.reachmobi.sportapp.data.model.LeagueDetailSingle
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.ui.fragment.league.info.LeagueInfoFragment.Companion.SEND_LEAGUE_TO_MAIN_VM
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

class LeagueInfoViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {
    val leagueId = ObservableField<String>()
    val leagueDetail = ObservableField<LeagueDetailSingle>()
    val coverImage = ObservableField<CoverImage>()

    fun getDetailLeague() {
        viewModelScope.launch {
            loading.set(true)
            leagueId.get()?.let {
                remoteRepository.getLeagueDetail(it).let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            leagueDetail.set(it.data?.leagues?.get(0))
                            _uiEvent.postValue(UIEvent(SEND_LEAGUE_TO_MAIN_VM, it.data?.leagues?.get(0)))
                            loading.set(false)
                        }
                        Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                    }
                }
            }
        }
    }
}