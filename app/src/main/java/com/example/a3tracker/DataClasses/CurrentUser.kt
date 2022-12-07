package com.example.a3tracker.DataClasses

data class CurrentUser(
    var ID: Int,
    val department_id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val location: Any,
    val phone_number: Any,
    val type: Int
)