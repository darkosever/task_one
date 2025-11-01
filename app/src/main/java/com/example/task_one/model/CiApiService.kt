package com.example.task_one.model

import retrofit2.http.GET

interface CiApiService {
    @GET("job/Ant/api/json")
    suspend fun getJobs(): CiResponse
}