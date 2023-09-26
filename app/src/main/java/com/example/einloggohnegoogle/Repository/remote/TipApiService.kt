package com.example.catfactsfriday.data.remote

import com.example.catfactsfriday.data.datamodels.FactsItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://catfact.ninja/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ItemApiService {
    @GET("/fact")
    suspend fun getItems(): FactsItem
}
object ItemApi {
    val retrofitService: ItemApiService by lazy { retrofit.create(ItemApiService::class.java) }
}