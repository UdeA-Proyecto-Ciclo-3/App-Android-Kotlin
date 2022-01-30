package com.developx.poi.interfaces

import com.developx.poi.models.Places
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend
    fun getPlaces(@Url url:String): Response<Places>
}