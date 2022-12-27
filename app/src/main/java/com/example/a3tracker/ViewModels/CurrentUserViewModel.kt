package com.example.a3tracker.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3tracker.DataClasses.LoginResponse
import com.example.a3tracker.Repo.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CurrentUser(
    var ID: Int = 0,
    val department_id: Int = 0,
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val location: String? = null,
    val phone_number: String? = null,
    val type: Int = 0,
    val loginResponse: LoginResponse = LoginResponse(123456,"",420),
    val imageUrl : String = ""
)

class CurrentUserViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(CurrentUser())
    private val uiState : StateFlow<CurrentUser> = _uiState.asStateFlow()

    fun getID():Int{
        return uiState.value.ID
    }

    fun getDepartmentId() : Int{
        return _uiState.value.department_id
    }

    fun getName(): String{
        return "${_uiState.value.first_name} ${_uiState.value.last_name}"
    }

    fun getEmail():String{
        return _uiState.value.email
    }

    fun getType():Int{
        return _uiState.value.type
    }

    fun getLocation():String?{
        return _uiState.value.location
    }

    fun getPhoneNumber():String?{
        return _uiState.value.phone_number
    }

    fun getImageUrl():String{
        return _uiState.value.imageUrl
    }

    fun getToken():String{
        return _uiState.value.loginResponse.token
    }

    fun getDeadline():Long{
        return _uiState.value.loginResponse.deadline
    }

    fun updateLoginResponse(deadline: Long,token: String,userId : Int ){
        _uiState.update { currentState ->
            currentState.copy(
                loginResponse = LoginResponse(deadline,token,userId)
            )
        }
    }

    fun updateUserId(newId : Int){
        _uiState.update { currentState ->
            currentState.copy(
                ID = newId
            )
        }
    }

    fun updateDepartmentId(newDepartment : Int){
        _uiState.update { currentState ->
            currentState.copy(
                department_id = newDepartment
            )
        }
    }

    fun updateEmail( newEmail : String){
        _uiState.update { currentState ->
            currentState.copy(
                email = newEmail
            )
        }
    }

    fun updateName(newFN : String, newLN : String){
        _uiState.update { currentState ->
            currentState.copy(
                first_name = newFN,
                last_name = newLN
            )
        }
    }

    fun updateLocation(newLocation : String){
        _uiState.update { currentState ->
            currentState.copy(
                location = newLocation
            )
        }
    }

    fun updatePhoneNumber(newPhoneNumber : String){
        _uiState.update { currentState ->
            currentState.copy(
                phone_number = newPhoneNumber
            )
        }
    }

    fun updateType(newType : Int){
        _uiState.update { currentState ->
            currentState.copy(
                type = newType
            )
        }
    }

    fun updateImage(newImage : String){
        _uiState.update { currentState ->
            currentState.copy(
                imageUrl = newImage
            )
        }
    }

    fun getCurrentUser(){
        val token = _uiState.value.loginResponse.token
        val userRepo = UserRepository()
        viewModelScope.launch {
            try {
                val response = userRepo.getCurrentUser(cuRequest = token)
                if(response.isSuccessful == true){
                    val responses = response.body().toString().trim().split(",")
                    val responsesToUse : MutableList<String> = mutableListOf()
                    for(r in responses){
                        val temp = r.split("=")[1]
                        responsesToUse.add(temp)
                    }
                    for(r in responsesToUse){
                        Log.i("CurrentUser", r)
                    }
                    _uiState.update { currentState ->
                        currentState.copy(
                            ID = responsesToUse[0].toInt(),
                            last_name = responsesToUse[1],
                            first_name = responsesToUse[2],
                            email = responsesToUse[3],
                            type = responsesToUse[4].toInt(),
                            location = responsesToUse[5],
                            phone_number = responsesToUse[6],
                            department_id = responsesToUse[7].toInt(),
                            imageUrl = responsesToUse[8],
                        )
                    }
                    Log.i("CurrentUser",getName())
                }
            } catch (ex: Exception){
                Log.i("CU VM",ex.message,ex)
            }
        }
    }

}

