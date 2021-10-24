package com.airlineassignment.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


val BASE_URL = "https://rosterbuster.aero/wp-content/uploads/"

val interceptor: HttpLoggingInterceptor =
    HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface RemoteDataService {

    @GET("dummy-response.json")
    suspend fun getRoster(): RosterDto

}

object RemoteDataAPI {
    val remoteDataService: RemoteDataService by lazy {
        retrofit.create(RemoteDataService::class.java)
    }
}