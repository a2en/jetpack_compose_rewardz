package io.github.a2en.rewardzjetpack.presentation.login

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.a2en.rewardzjetpack.domain.model.User
import io.github.a2en.rewardzjetpack.domain.repository.UserRepository
import io.github.a2en.rewardzjetpack.presentation.util.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredPhone -> {
                _state.value = state.value.copy(
                    phoneNumber = event.value
                )
            }
            is LoginEvent.Login -> {
                loginUser()
            }
        }
    }

    private fun loginUser() {
        if (state.value.phoneNumber.isBlank()) {
            _state.value = state.value.copy(
                phoneErrorMsg = "Please enter a phone number"
            )
            return
        } else {
            _state.value = state.value.copy(
                phoneErrorMsg = ""
            )
        }

        viewModelScope.launch {
            repository.getUserByPhoneNumber(state.value.phoneNumber).also {
               if(it!=null){
                   Toast.makeText(
                       application,
                       "User found with username ${it.userName} and email ${it.email}",
                       Toast.LENGTH_LONG
                   ).show()
               }else{
                   Toast.makeText(
                       application,
                       "User not found. Please register",
                       Toast.LENGTH_LONG
                   ).show()
               }
            }
        }



    }

}