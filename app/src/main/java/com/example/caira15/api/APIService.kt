package com.example.caira15.api

import com.example.caira15.model.DataLogin
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {



    @GET("users")
    suspend fun getAllUsers(): Response<APIResponse>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<APIResponse>

    @POST("login/")
    suspend fun login( @Body dataLogin: DataLogin): Response<APIResponseLogin>

}