package us.reachmobi.sportapp.ui.activity

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import us.reachmobi.sportapp.base.BaseViewModel
import us.reachmobi.sportapp.data.local.CoverImage
import us.reachmobi.sportapp.util.UIEvent

class MainViewModel : BaseViewModel() {
    val coverImage = ObservableField<CoverImage>()
}