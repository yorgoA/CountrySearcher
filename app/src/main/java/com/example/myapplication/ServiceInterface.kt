package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("all")
    fun getAllCountries(): Call<List<ApiResponse>>
}
