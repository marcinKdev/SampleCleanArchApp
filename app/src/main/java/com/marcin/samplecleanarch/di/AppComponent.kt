package com.marcin.samplecleanarch.di

import com.marcin.samplecleanarch.SampleCleanArchApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<SampleCleanArchApplication> {
            override fun inject(app: SampleCleanArchApplication)
}