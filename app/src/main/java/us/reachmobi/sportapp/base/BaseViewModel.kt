package us.reachmobi.sportapp.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import us.reachmobi.sportapp.util.Resource
import us.reachmobi.sportapp.util.UIEvent

abstract class BaseViewModel : ViewModel() {

    val _uiEvent: MutableLiveData<UIEvent<Int>> = MutableLiveData()
    val uiEvent: LiveData<UIEvent<Int>> get() = _uiEvent

    val _error: MutableLiveData<UIEvent<Int>> = MutableLiveData()
    val error: LiveData<UIEvent<Int>> get() = _error

    val loading = ObservableBoolean(false)

}