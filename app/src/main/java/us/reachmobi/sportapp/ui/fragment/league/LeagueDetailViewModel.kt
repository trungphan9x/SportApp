package us.reachmobi.sportapp.ui.fragment.league

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.model.League

class LeagueDetailViewModel : BaseViewModel() {
    val league = ObservableField<League>()
}