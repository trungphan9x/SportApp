package us.reachmobi.sportapp.ui.fragment.league.match

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.sharedViewModel
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.data.remote.response.Events
import us.reachmobi.sportapp.data.remote.response.SearchEvents
import us.reachmobi.sportapp.databinding.FragmentMatchListBinding
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.ui.rvadapter.MatchRVAdapter
import us.reachmobi.sportapp.util.UIEvent

class MatchListFragment : BaseFragment<FragmentMatchListBinding>() {

    private val viewModel: MatchViewModel by viewModel()
    private val mainVM: MainViewModel by sharedViewModel()
    private val adapter = MatchRVAdapter()

    override fun layoutResId() = R.layout.fragment_match_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        getExtraData()
        initRecyclerView()
    }

    private fun getExtraData() {
        arguments?.getString(LEAGUE_ID)?.let { leagueId ->
            viewModel.leagueId.set(leagueId)
            arguments?.getBoolean(IS_LAST_MATCH)?.let { isLastMatch ->
                viewModel.isLastMatch.set(isLastMatch)
                when (isLastMatch) {
                    true -> viewModel.getLastMatchByLeagueId(leagueId)
                    false -> viewModel.getNextMatchByLeagueId(leagueId)
                }
            }
        }
    }

    private fun initData() {
        binding.vm = viewModel
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())
        mainVM.uiEvent.observe(viewLifecycleOwner, onUiEvent())
    }

    private fun initRecyclerView() {
        binding.rvMatch.adapter = adapter.apply {
            setOnItemClickListener { position, League ->

            }
        }
    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {
                UPDATE_RV_MATCHES -> {
                    (it.second as? Events)?.let {
                        adapter.submitList(it.events)
                    }

                    (it.second as? SearchEvents)?.let {
                        adapter.submitList(it.event)
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
        const val UPDATE_RV_MATCHES = 0
        const val LEAGUE_ID = "leagueId"
        const val IS_LAST_MATCH = "is_last_match"
        const val IS_SEARCH = "is_search"

        fun newInstance(leagueId: String? = null, isLastMatch: Boolean? = null, isSearch: Boolean = false) = MatchListFragment().apply {
            arguments = Bundle().apply {
                putString(LEAGUE_ID, leagueId)
                isLastMatch?.let { putBoolean(IS_LAST_MATCH, it) }
                putBoolean(IS_SEARCH, isSearch)
            }
        }
    }

}