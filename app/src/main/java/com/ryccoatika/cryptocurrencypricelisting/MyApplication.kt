package com.ryccoatika.cryptocurrencypricelisting

import android.app.Application
import com.ryccoatika.cryptocurrencypricelisting.core.di.networkModule
import com.ryccoatika.cryptocurrencypricelisting.core.di.repositoryModule
import com.ryccoatika.cryptocurrencypricelisting.ui.di.useCaseModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}