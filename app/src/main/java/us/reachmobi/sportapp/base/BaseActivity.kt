package us.reachmobi.sportapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import us.reachmobi.sportapp.util.ERROR
import us.reachmobi.sportapp.util.UIEvent

abstract class BaseActivity<T : ViewDataBinding>() : AppCompatActivity() {

    protected lateinit var binding: T
        private set

    abstract fun layoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId())
        binding.lifecycleOwner = this
    }

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
                            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
