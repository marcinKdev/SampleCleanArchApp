package com.marcin.samplecleanarch

import android.app.Application
import com.marcin.samplecleanarch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SampleCleanArchApplication : Application(), HasAndroidInjector {

            @Inject
            lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

            override fun onCreate() {
                        super.onCreate()
                        DaggerAppComponent.create().inject(this)
            }

            override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}