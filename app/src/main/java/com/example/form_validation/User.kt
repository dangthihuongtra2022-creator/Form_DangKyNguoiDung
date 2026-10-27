package com.example.form_validation

import java.io.Serializable

data class User(
    val username: String,
    val email: String,
    val phone: String
) : Serializable