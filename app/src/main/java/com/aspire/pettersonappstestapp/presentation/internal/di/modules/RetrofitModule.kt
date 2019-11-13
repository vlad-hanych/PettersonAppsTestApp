package com.aspire.pettersonappstestapp.presentation.internal.di.modules

import com.aspire.pettersonappstestapp.data.ServerAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideServerAPI(gson: Gson): ServerAPI {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

        val okHttpClientBuilder = OkHttpClient.Builder()

        ///addInterceptor(okHttpClientBuilder)

        val client = okHttpClientBuilder.build()

        val retrofit = retrofitBuilder.client(client).build()

        return retrofit.create(ServerAPI::class.java)
    }

    /*private fun addInterceptor(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.interceptors().add(Interceptor { chain ->
            val request = chain.request()
            // Check the method first.

            val url = request.url()

            Log.d("Request URL", url.toString())

            val response = chain.proceed(request)

            val responseString = response.body()?.string()

            Log.d("Response", responseString)

            chain.proceed(request)
        })
    }*/

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
}