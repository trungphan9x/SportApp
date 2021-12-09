package us.reachmobi.sportapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import us.reachmobi.sportapp.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.data.remote.response.Leagues
import us.reachmobi.sportapp.ui.rvadapter.LeagueRVAdapter
import us.reachmobi.sportapp.util.UIEvent

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter = LeagueRVAdapter()

    override fun layoutResId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
        viewModel.getMyLeagues()
        setListener()

    }

    private fun initData() {
        binding.vm = viewModel
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())
    }

    private fun initRecyclerView() {
        binding.rvLeague.adapter = adapter.apply {
            setOnItemClickListener { position, league ->
                getNav(binding.root).navigate(R.id.action_homeFragment_to_leagueDetailFragment, bundleOf("league" to league))
            }
        }
    }

    override fun onUiEvent() = Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {
                UPDATE_RV_LEAGUE -> {
                    (it.second as? Leagues)?.let {
                        adapter.submitList(it.leagues)
                    }
                }
            }
        }
    }

    private fun setListener() {
        binding.tvTitle.setOnClickListener {
            getNav(binding.root).navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.ivSearchIcon.setOnClickListener {
            getNav(binding.root).navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    companion object {
        const val UPDATE_RV_LEAGUE = 0
    }

}