package io.github.a2en.rewardzjetpack.presentation.register

import androidx.compose.ui.focus.FocusState

sealed class RegisterEvent{
    data class EnteredUsername(val value: String): RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredPhone(val value:String) : RegisterEvent()
    object Register: RegisterEvent()
}

