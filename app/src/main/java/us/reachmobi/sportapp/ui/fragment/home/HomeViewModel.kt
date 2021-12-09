package us.reachmobi.sportapp.ui.fragment.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.enums.Status
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.ui.fragment.home.HomeFragment.Companion.UPDATE_RV_LEAGUE
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

class HomeViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {

    fun getMyLeagues() {
        viewModelScope.launch {
            remoteRepository.getMyLeagues().let {
                when (it.status) {
                    Status.SUCCESS -> _uiEvent.postValue(UIEvent(UPDATE_RV_LEAGUE, it.data))
                    Status.ERROR -> _error.postValue(UIEvent(ERROR, it.message))
                }
            }
        }
    }
}