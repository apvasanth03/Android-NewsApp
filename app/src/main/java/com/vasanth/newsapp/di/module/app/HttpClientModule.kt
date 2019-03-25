package com.vasanth.newsapp.di.module.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vasanth.httpclient.HttpClient
import com.vasanth.httpclient.urlconnection.UrlConnectionHttpClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class HttpClientModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providesGson(): Gson {
            return GsonBuilder().create()
        }

    }

    @Binds
    abstract fun bindHttpClient(urlConnectionHttpClientImpl: UrlConnectionHttpClientImpl): HttpClient

}