package us.reachmobi.sportapp.di

import org.koin.dsl.module
import us.reachmobi.sportapp.data.repository.remote.RemoteRepository
import us.reachmobi.sportapp.data.repository.remote.RemoteRepositoryImpl

val repositoryModule = module {
   factory<RemoteRepository> { RemoteRepositoryImpl(get()) }

}