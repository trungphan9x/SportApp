package us.reachmobi.sportapp.di

import org.koin.dsl.module
import retrofit2.Retrofit
import us.reachmobi.sportapp.data.remote.api.SportApiService

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(SportApiService::class.java) }
}