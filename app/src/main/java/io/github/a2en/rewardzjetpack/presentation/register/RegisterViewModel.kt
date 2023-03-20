package io.github.a2en.rewardzjetpack.presentation.register

import android.app.Application
import android.text.TextUtils
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
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository,
    private val application: Application
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    private val _eventFlow = MutableSharedFlow<Screen>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _state.value = state.value.copy(
                    userName = event.value
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }
            is RegisterEvent.EnteredPhone -> {
                _state.value = state.value.copy(
                    phoneNumber = event.value
                )
            }
            is RegisterEvent.Register -> {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        var isValid = true
        if (state.value.userName.isBlank()) {
            isValid = false
            _state.value = state.value.copy(
                userNameErrorMsg = "Please enter a username"
            )
        } else {
            _state.value = state.value.copy(
                userNameErrorMsg = ""
            )
        }

        if (!isValidEmail(state.value.email)) {
            isValid = false
            _state.value = state.value.copy(
                emailErrorMsg = "Please enter a valid email"
            )
        }
        if (state.value.email.isBlank()) {
            isValid = false
            _state.value = state.value.copy(
                emailErrorMsg = "Please enter an email"
            )
        }

        if (state.value.email.isNotBlank() && isValidEmail(state.value.email)) {
            _state.value = state.value.copy(
                emailErrorMsg = ""
            )
        }

        if (state.value.phoneNumber.isBlank()) {
            isValid = false
            _state.value = state.value.copy(
                phoneErrorMsg = "Please enter a phone number"
            )
        }else {
            _state.value = state.value.copy(
                phoneErrorMsg = ""
            )
        }

        if (isValid) {
            viewModelScope.launch {
                repository.insertUser(
                    User(
                        userName = state.value.userName,
                        email = state.value.email,
                        phone = state.value.phoneNumber,
                        id = null
                    )
                )
                _state.value = RegisterState()
              _eventFlow.emit(Screen.LoginScreen)
            }
            Toast.makeText(application, "User successfully created. Login to continue", Toast.LENGTH_SHORT).show()

        }


    }


    private fun isValidEmail(target: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}