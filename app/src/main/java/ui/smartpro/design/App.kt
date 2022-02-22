package ui.smartpro.design

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ui.smartpro.design.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin  {
            androidContext(this@App)
            modules(
                arrayListOf(
                    appModule
                )
            )
        }
    }
}