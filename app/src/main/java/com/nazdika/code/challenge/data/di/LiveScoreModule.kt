package com.nazdika.code.challenge.data.di

import android.content.Context
import android.provider.Settings
import com.nazdika.code.challenge.data.datasouce.remote.NazdikaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.TimeZone
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LiveScoreModule {
    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val request = chain.request()
            request.newBuilder().addHeader(
                "X-Seconds-From-UTC",
                getTimeZoneOffsetFromUTC().toString()
            )
                .addHeader(
                    "X-UDID",
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                ).build()
            return@Interceptor chain.proceed(request)
        }).addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(httpClient: OkHttpClient): NazdikaApi {
        return Retrofit.Builder().baseUrl("https://api.nazdika.com/v2/")
            .client(httpClient).addConverterFactory(GsonConverterFactory.create())
            .build().create(NazdikaApi::class.java)
    }

    private fun getTimeZoneOffsetFromUTC(): Int {
        val timeZone = TimeZone.getDefault()
        val now = Date()
        return timeZone.getOffset(now.time) / 1000
    }
}