package com.example.a3tracker.ViewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CurrentUser(
    var ID: Int = 0,
    val department_id: Int = 0,
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val location: Any = "",
    val phone_number: Any = 0,
    val type: Int = 0
)

class CurrentUserViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(CurrentUser())
    private val uiState : StateFlow<CurrentUser> = _uiState.asStateFlow()

    fun getID():Int{
        return uiState.value.ID
    }

}

