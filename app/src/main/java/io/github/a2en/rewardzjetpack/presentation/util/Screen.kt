package io.github.a2en.rewardzjetpack.presentation.util

sealed class Screen(val route: String) {
    object RegisterScreen: Screen("register_screen")
    object LoginScreen: Screen("login_screen")
}
