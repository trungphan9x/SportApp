package us.reachmobi.sportapp.ui.fragment.league

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import us.reachmobi.sportapp.databinding.FragmentLeagueDetailBinding
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.util.UIEvent

class LeagueDetailFragment : BaseFragment<FragmentLeagueDetailBinding>() {

    private val tabs = arrayListOf<String>("INFO", "TEAM", "LAST MATCH", "NEXT MATCH")
    private val viewModel: LeagueDetailViewModel by viewModel()
    private val mainVM : MainViewModel by sharedViewModel()
    private val args: LeagueDetailFragmentArgs by navArgs<LeagueDetailFragmentArgs>()

    override fun layoutResId() = R.layout.fragment_league_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtraData()
        initData()
        setViewPager()
        setListener()
    }

    private fun initData() {
        binding.vm = viewModel
        binding.mainVM = mainVM
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())
    }

    private fun getExtraData() {
        args.league?.let {
            viewModel.league.set(it)
        }
    }

    private fun setViewPager() {
        viewModel.league.get()?.idLeague?.let {
            val adapter = LeaguePagerAdapter(childFragmentManager, lifecycle, tabs, it)
            binding.viewPager.adapter = adapter
            //binding.viewPager.offscreenPageLimit = 2

            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let { binding.viewPager.setCurrentItem(it, true) }
                }

            })

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = tabs[position]
            }.attach()
        }
    }

    private fun setListener() {
        binding.ivLeft.setOnClickListener {
            getNav(binding.root).navigateUp()
        }

        binding.ivRight.setOnClickListener {
            getNav(binding.root).navigate(R.id.action_leagueDetailFragment_to_searchFragment)
        }
    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {

            }
        }
    }
}