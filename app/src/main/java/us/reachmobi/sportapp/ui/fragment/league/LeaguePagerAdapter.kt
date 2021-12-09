package us.reachmobi.sportapp.ui.fragment.league

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import us.reachmobi.sportapp.data.model.League
import us.reachmobi.sportapp.ui.fragment.league.info.LeagueInfoFragment
import us.reachmobi.sportapp.ui.fragment.league.match.MatchListFragment
import us.reachmobi.sportapp.ui.fragment.league.teams.TeamListFragment

class LeaguePagerAdapter(
    fm: FragmentManager,
    lc: Lifecycle,
    private val tabs: ArrayList<String>,
    private val leagueId: String,
) : FragmentStateAdapter(fm, lc) {
    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LeagueInfoFragment.newInstance(leagueId = leagueId)
            1 -> TeamListFragment.newInstance(leagueId = leagueId)
            2 -> MatchListFragment.newInstance(leagueId = leagueId, isLastMatch = true)
            3 -> MatchListFragment.newInstance(leagueId = leagueId, isLastMatch = false)
            else -> Fragment()
        }
    }

}