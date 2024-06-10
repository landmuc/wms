package com.landmuc.wms

import android.app.Application
import com.landmuc.network.di.supabaseClientModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

   override fun onCreate() {
       super.onCreate()

       // List of Koin Modules
       val moduleList = listOf(
           supabaseClientModule
       )

       startKoin {
           androidLogger() // Log Koin into Android logger
           androidContext(this@MainApplication)

           modules(moduleList)
       }

   }
}