package us.reachmobi.sportapp.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import us.reachmobi.sportapp.BuildConfig
import us.reachmobi.sportapp.data.`object`.Constant.OkHttp_TIMEOUT
import us.reachmobi.sportapp.util.ResponseHandler
import java.util.concurrent.TimeUnit


val networkModule = module {
    factory { buildOkHttpClient() }
    single { buildRetrofit(get()) }
    factory { ResponseHandler() }
}


fun buildOkHttpClient() = OkHttpClient.Builder().apply {
    connectTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(OkHttp_TIMEOUT, TimeUnit.SECONDS)
    addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
    addInterceptor(Interceptor { chain ->
        chain.proceed(chain.request()
            .newBuilder()
            .build())
    })
}

fun buildRetrofit(okHttpClient: OkHttpClient.Builder): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient.build())
        .build()
}