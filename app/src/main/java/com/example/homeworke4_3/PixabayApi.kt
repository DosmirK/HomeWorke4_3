package com.example.homeworke4_3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun getImages(
        @Query("key") key: String = "25007027-7418deb977c638792f4bfb99f",
        @Query("q") searchWord: String,
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int = 1
    ): Call<PixabayModel>
}