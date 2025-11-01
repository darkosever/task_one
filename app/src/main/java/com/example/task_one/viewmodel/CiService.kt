package com.example.task_one.viewmodel

import com.example.task_one.model.CiApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CiService {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ci-builds.apache.org/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: CiApiService = retrofit.create(CiApiService::class.java)
}
