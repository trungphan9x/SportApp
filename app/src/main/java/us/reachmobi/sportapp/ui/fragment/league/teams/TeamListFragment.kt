package us.reachmobi.sportapp.ui.fragment.league.teams

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.sharedViewModel
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import us.reachmobi.sportapp.databinding.FragmentTeamListBinding
import us.reachmobi.sportapp.ui.rvadapter.TeamRVAdapter
import us.reachmobi.sportapp.util.UIEvent
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.data.remote.response.Teams
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.ui.fragment.search.SearchViewModel

class TeamListFragment : BaseFragment<FragmentTeamListBinding>() {

    private val viewModel: TeamListViewModel by viewModel()
    private val searchVM: SearchViewModel by sharedViewModel()
    private val mainVM: MainViewModel by sharedViewModel()
    private val adapter = TeamRVAdapter()

    override fun layoutResId() = R.layout.fragment_team_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        getExtraData()
        initRecyclerView()
    }

    private fun initData() {
        binding.vm = viewModel
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())
        searchVM.uiEvent.observe(viewLifecycleOwner, onUiEvent())
        mainVM.uiEvent.observe(viewLifecycleOwner, onUiEvent())
    }

    private fun getExtraData() {
        viewModel.leagueId.set(arguments?.getString(LEAGUE_ID) ?: "")
        viewModel.getTeamListByLeagueId()
    }

    private fun initRecyclerView() {
        binding.rvTeam.adapter = adapter.apply {
            setOnItemClickListener { position, League ->

            }
        }
    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {
                UPDATE_RV_TEAMS -> {
                    (it.second as? Teams)?.let {
                        adapter.submitList(it.teams)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    companion object {
        const val UPDATE_RV_TEAMS = 0
        const val IS_SEARCH = "is_search"
        const val LEAGUE_ID = "leagueId"

        fun newInstance(leagueId: String? = null, isSearch: Boolean? = null) = TeamListFragment().apply {
            arguments = Bundle().apply {
                putString(LEAGUE_ID, leagueId)
                isSearch?.let { putBoolean(IS_SEARCH, it) }
            }
        }
    }

}