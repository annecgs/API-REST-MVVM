package com.example.apirestmvvm.apis

import com.example.apirestmvvm.VolumesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookSearchService {
    @GET("/books/v1/volumes")
    fun searchVolumes(
        @Query("q") query: String?,
        @Query("inauthor") author: String?,
        @Query("key") apiKey: Unit,
    ): Call<VolumesResponse?>?
}
