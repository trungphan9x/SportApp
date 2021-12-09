package us.reachmobi.sportapp.di

import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import us.reachmobi.sportapp.ui.activity.MainViewModel
import us.reachmobi.sportapp.ui.fragment.home.HomeViewModel
import us.reachmobi.sportapp.ui.fragment.league.LeagueDetailViewModel
import us.reachmobi.sportapp.ui.fragment.league.info.LeagueInfoViewModel
import us.reachmobi.sportapp.ui.fragment.league.match.MatchViewModel
import us.reachmobi.sportapp.ui.fragment.league.teams.TeamListViewModel
import us.reachmobi.sportapp.ui.fragment.search.SearchViewModel

val viewModelsModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { LeagueDetailViewModel() }
    viewModel { LeagueInfoViewModel(get()) }
    viewModel { MatchViewModel(get()) }
    viewModel { TeamListViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}