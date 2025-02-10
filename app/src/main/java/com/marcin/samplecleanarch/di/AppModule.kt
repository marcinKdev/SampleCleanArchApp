package com.marcin.samplecleanarch.di

import com.google.gson.GsonBuilder
import com.marcin.data.BASE_URL
import com.marcin.data.GithubApiInterface
import com.marcin.data.repositories.RepositoriesRepoImpl
import com.marcin.domain.RepositoriesRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

      @Binds
      abstract fun provideRepositoriesRepo(repositoriesRepoImpl: RepositoriesRepoImpl): RepositoriesRepo

      companion object {

            @Provides
            @Singleton
            fun provideOkHttpClient(): OkHttpClient {
                  return OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .build()
            }

            @Provides
            @Singleton
            fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
                  return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .client(okHttpClient)
                        .build()
            }

            @Provides
            @Singleton
            fun provideApiInterface(retrofit: Retrofit) = retrofit.create(GithubApiInterface::class.java)
      }

}