package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GameInterface {
    @GET("questions")
    fun getGeographyQuestions(@Query("categories") category: String = "geography"): Call<List<Question>>
}
