package ir.fallahpoor.cleankmmdemo

import android.app.Application
import ir.fallahpoor.cleankmmdemo.data.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}