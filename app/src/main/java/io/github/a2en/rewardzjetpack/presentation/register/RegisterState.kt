package io.github.a2en.rewardzjetpack.presentation.register

data class RegisterState(
    val phoneNumber: String = "",
    val email : String = "",
    val userName : String = "",
    val emailErrorMsg: String = "",
    val userNameErrorMsg: String = "",
    val phoneErrorMsg: String = "",
)
