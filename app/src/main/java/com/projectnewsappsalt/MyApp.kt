package com.projectnewsappsalt

import android.app.Application
import com.projectnewsappsalt.data.di.networkModule
import com.projectnewsappsalt.data.di.repositoryModule
import com.projectnewsappsalt.presentation.di.useCaseModule
import com.projectnewsappsalt.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.DEBUG)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}