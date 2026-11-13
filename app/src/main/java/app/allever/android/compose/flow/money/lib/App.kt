package app.allever.android.compose.flow.money.lib

import android.app.Application
class App: Application() {
    companion object {
        lateinit var instance: App
        //context
        val context get() = instance.applicationContext
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}