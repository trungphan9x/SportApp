package us.reachmobi.sportapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import us.reachmobi.sportapp.di.*
import us.reachmobi.sportapp.util.SharedPreferences.initSharedPreferences

class SportApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initSharedPreferences(this.applicationContext)

        startKoin{
            androidLogger()
            androidContext(this@SportApp)
            modules(listOf(viewModelsModule, networkModule, repositoryModule, apiModule, roomModule))
        }
    }

    companion object {
        lateinit var INSTANCE: SportApp
    }
}