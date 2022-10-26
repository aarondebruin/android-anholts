package com.example.anholts

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/auth/buttonlog")
    fun getData(): Call<List<dataModelItem>>

}