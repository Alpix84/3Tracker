package com.example.a3tracker.Interfaces

import com.example.a3tracker.DataClasses.CurrentUser
import com.example.a3tracker.DataClasses.LoginRequest
import com.example.a3tracker.DataClasses.LoginResponse
import com.example.a3tracker.Objects.ApiClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface{


    @GET("user")
    fun getCurrentUser(): Call<CurrentUser>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): ApiInterface? {
            return ApiClient.client?.create(ApiInterface::class.java)
        }
    }

}