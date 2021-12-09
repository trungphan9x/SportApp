package us.reachmobi.sportapp.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseActivity
import us.reachmobi.sportapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.util.UIEvent

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController }

    private val mainVM by viewModel<MainViewModel>()

    override fun layoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setListener()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.leagueDetailFragment) {
            navController.navigateUp()
        } else {
            super.onBackPressed()
        }
    }

    private fun initData() {
        binding.vm = mainVM
        mainVM.uiEvent.observe(this, onUiEvent())
    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {

            }
        }
    }

    private fun setListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {

                }
                R.id.leagueDetailFragment -> {

                }
            }
        }
    }
}