package com.example.a3tracker.Repo

import com.example.a3tracker.DataClasses.GetCURequest
import com.example.a3tracker.DataClasses.GetCUResponse
import com.example.a3tracker.DataClasses.LoginRequest
import com.example.a3tracker.DataClasses.LoginResponse
import com.example.a3tracker.Interfaces.ApiInterface
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  ApiInterface.getApi()?.loginUser(loginRequest = loginRequest)
    }

    suspend fun getCurrentUser(cuRequest: String): Response<GetCUResponse> {
        return ApiInterface.getApi()!!.getCurrentUser(token = cuRequest)
    }
}