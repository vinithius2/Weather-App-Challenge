package com.vinithius.weather_app_challenge.di


import com.vinithius.weather_app_challenge.BuildConfig
import com.vinithius.weather_app_challenge.datasource.repository.WeatherAppRemoteDataSource
import com.vinithius.weather_app_challenge.datasource.repository.WeatherAppRepository
import com.vinithius.weather_app_challenge.ui.WeatherAppViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val repositoryModule = module {
    single { get<Retrofit>().create(WeatherAppRemoteDataSource::class.java) }
}

val repositoryDataModule = module {
    single { WeatherAppRepository(get()) }
}

val viewModelModule = module {
    single { WeatherAppViewModel(get()) }
}

val networkModule = module {
    single { retrofit() }
}

fun retrofit(): Retrofit {

    val privateKey = BuildConfig.PRIVATE_KEY

    val clientInterceptor = Interceptor { chain: Interceptor.Chain ->
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("appid", privateKey)
            .addQueryParameter("units", "metric")
            .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addNetworkInterceptor(clientInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
