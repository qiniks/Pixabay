package com.example.pixabay.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("/api/")
    fun getImageByWord(
        @Query("key") key: String = "28276142-ca53c7c578199da29ba5b21b5",
        @Query("q") keyword: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<PixabayModel>
}