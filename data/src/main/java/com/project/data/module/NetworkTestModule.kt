package com.project.data.module

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.data.BuildConfig
import com.project.data.service.MainService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by achmad.fachrudin on 1-Jun-19
 */
val networkTestModule = module {

    factory<Interceptor> {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.API_BASE)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    /* service */
    factory { get<Retrofit>().create(MainService::class.java) }
}