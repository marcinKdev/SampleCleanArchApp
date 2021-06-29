package com.marcin.samplecleanarch.di

import com.google.gson.GsonBuilder
import com.marcin.data.GithubApiInterface
import com.marcin.data.repositories.RepositoriesRepoImpl
import com.marcin.domain.RepositoriesRepo
import com.marcin.domain.SchedulersProvider
import com.marcin.samplecleanarch.MainActivity
import com.marcin.samplecleanarch.SchedulersProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class AppModule {

            @Binds
            abstract fun provideSchedulersProvider(schedulersProviderImpl: SchedulersProviderImpl) : SchedulersProvider

            @Binds
            abstract fun provideRepositoriesRepo(repositoriesRepoImpl: RepositoriesRepoImpl) : RepositoriesRepo

            @ContributesAndroidInjector
            abstract fun contributeMainActivityInjector() : MainActivity

            @Module
            companion object {

                        @JvmStatic
                        @Provides
                        @Singleton
                        fun provideOkHttpClient(): OkHttpClient {
                                    return OkHttpClient.Builder()
                                                .connectTimeout(120, TimeUnit.SECONDS)
                                                .readTimeout(120, TimeUnit.SECONDS)
                                                .build()
                        }

                        @JvmStatic
                        @Provides
                        @Singleton
                        fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
                                    return Retrofit.Builder()
                                                .baseUrl("https://api.github.com/")
                                                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                                                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                                .client(okHttpClient)
                                                .build()
                        }

                        @JvmStatic
                        @Provides
                        @Singleton
                        fun provideApiInterface(retrofit: Retrofit) = retrofit.create(GithubApiInterface::class.java)
            }

}