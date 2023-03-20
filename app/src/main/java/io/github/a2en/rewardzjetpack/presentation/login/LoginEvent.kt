package io.github.a2en.rewardzjetpack.presentation.login


sealed class LoginEvent{
    data class EnteredPhone(val value:String) : LoginEvent()
    object Login: LoginEvent()
}

