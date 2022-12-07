package com.example.a3tracker.Interfaces

import com.example.a3tracker.DataClasses.CurrentUser
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{


    @GET("user")
    fun getCurrentUser(): Call<CurrentUser>
}