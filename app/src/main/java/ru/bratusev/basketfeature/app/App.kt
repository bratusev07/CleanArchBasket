package ru.bratusev.basketfeature.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.bratusev.basketfeature.di.appModule
import ru.bratusev.basketfeature.di.dataModule
import ru.bratusev.basketfeature.di.domainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}