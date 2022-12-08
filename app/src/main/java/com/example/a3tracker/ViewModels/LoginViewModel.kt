package com.example.a3tracker.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3tracker.DataClasses.LoginRequest
import com.example.a3tracker.DataClasses.LoginResponse
import com.example.a3tracker.Enums.LoginResult
import com.example.a3tracker.Repo.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    companion object{
        val TAG: String = LoginViewModel::class.java.simpleName
    }

    val loginResult: MutableLiveData<LoginResult> = MutableLiveData()
    private val userRepo = UserRepository()

    fun login(email: String, password: String) {
        loginResult.value = LoginResult.LOADING
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            loginResult.value = LoginResult.INVALID_CREDENTIALS
            return
        }
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Login response ${response.body()}")
                    loginResult.value = LoginResult.SUCCESS
                } else {
                    Log.d(TAG, "Login error response ${response?.errorBody()}")
                    loginResult.value = LoginResult.INVALID_CREDENTIALS
                }

            } catch (ex: Exception) {
                Log.e(TAG, ex.message, ex)
                loginResult.value = LoginResult.UNKNOWN_ERROR
            }
        }
    }
}