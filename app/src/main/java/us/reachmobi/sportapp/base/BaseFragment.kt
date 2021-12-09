package us.reachmobi.sportapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    protected lateinit var binding: T
        private set

    val anim = navOptions {
        anim {
            enter = R.anim.bounce
            popEnter = R.anim.fade_in

        }
    }

    abstract fun layoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            layoutResId(),
            null,
            true
        )

        binding.lifecycleOwner = this
        return binding.root
    }

    fun getNav(view: View) = Navigation.findNavController(view)

    abstract fun onUiEvent(): Observer<UIEvent<Int>>

    fun observeError(viewModel: BaseViewModel) {
        viewModel.error.observe(this, onError())
    }

    private fun onError(): Observer<UIEvent<Int>> {
        return Observer {
            it?.getData()?.let {
                when (it.first) {
                    ERROR -> {
                        (it.second as? String)?.let {
                            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}