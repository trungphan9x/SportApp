package us.reachmobi.sportapp.ui.fragment.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class SearchPagerAdapter(
    fm: FragmentManager,
    lc: Lifecycle,
    private val mapTab: HashMap<String, Fragment>
) : FragmentStateAdapter(fm, lc) {
    override fun getItemCount(): Int {
        return  mapTab.size
    }

    override fun createFragment(position: Int): Fragment {
        return mapTab.values.toTypedArray()[position]
    }

}