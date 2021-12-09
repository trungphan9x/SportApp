package us.reachmobi.sportapp.ui.fragment.league.info

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.base.BaseFragment
import us.reachmobi.sportapp.data.local.CoverImage
import us.reachmobi.sportapp.data.model.LeagueDetailSingle
import us.reachmobi.sportapp.databinding.FragmentLeagueInfoBinding
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.util.UIEvent

class LeagueInfoFragment : BaseFragment<FragmentLeagueInfoBinding>() {

    override fun layoutResId() = R.layout.fragment_league_info

    private val viewModel: LeagueInfoViewModel by viewModel()
    private val mainVM : MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        getExtraData()
        viewModel.getDetailLeague()

    }

    private fun getExtraData() {
        viewModel.leagueId.set(arguments?.getString(LEAGUE_ID) ?: "")
    }

    private fun initData() {
        binding.vm = viewModel
        viewModel.uiEvent.observe(viewLifecycleOwner, onUiEvent())

    }

    override fun onUiEvent()= Observer<UIEvent<Int>> {
        it.getData()?.let {
            when(it.first) {
                SEND_LEAGUE_TO_MAIN_VM -> {
                    (it.second as? LeagueDetailSingle)?.let {
                        val coverImage = CoverImage(urlBigPhoto = it.strFanart1, urlSmallPhoto = it.strBadge, title = it.strLeagueAlternate, subtitle = it.strCountry, ctLayoutTitle = it.strLeague)
                        mainVM.coverImage.set(coverImage)
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
        const val SEND_LEAGUE_TO_MAIN_VM = 0
        const val LEAGUE_ID = "leagueId"

        fun newInstance(leagueId: String) = LeagueInfoFragment().apply {
            arguments = Bundle().apply {
                putString(LEAGUE_ID, leagueId)
            }
        }
    }

}