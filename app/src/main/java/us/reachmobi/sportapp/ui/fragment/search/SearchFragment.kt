package us.reachmobi.sportapp.ui.fragment.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import us.reachmobi.sportapp.data.remote.response.Teams
import us.reachmobi.sportapp.databinding.FragmentSearchBinding
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.ui.rvadapter.TeamRVAdapter
import us.reachmobi.sportapp.util.UIEvent

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener {

    private val adapter = TeamRVAdapter()

    private val viewModel: SearchViewModel by viewModel()
    private val mainVM : MainViewModel by sharedViewModel()

    override fun layoutResId() = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setListener()
        initSearch()
        initRecyclerView()
    }

    private fun initData() {
        binding.vm = viewModel
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())
        mainVM.uiEvent.observe(viewLifecycleOwner, onUiEvent())
    }

    private fun initSearch() {
        binding.searchView.apply {
            isIconifiedByDefault = true
            isFocusable = true
            isIconified = false
//            requestFocusFromTouch()
            setOnQueryTextListener(this@SearchFragment)
        }

    }

    private fun initRecyclerView() {
        binding.rvTeam.adapter = adapter.apply {
            setOnItemClickListener { position, League ->

            }
        }
    }

    private fun setListener() {
        binding.ivLeft.setOnClickListener {
            getNav(binding.root).navigateUp()
        }
    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {
                UPDATE_RV_TEAMS_SEARCH -> {
                    (it.second as? Teams)?.let {
                        adapter.submitList(it.teams)
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(keyword: String?): Boolean {
        keyword?.let {
            viewModel.getTeamListWithSearch(it)
        }
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    companion object {
        const val UPDATE_RV_TEAMS_SEARCH = 0;
    }
}