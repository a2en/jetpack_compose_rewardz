package io.github.a2en.rewardzjetpack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.a2en.rewardzjetpack.R
import io.github.a2en.rewardzjetpack.presentation.register.RegisterEvent
import io.github.a2en.rewardzjetpack.presentation.register.RegisterState
import io.github.a2en.rewardzjetpack.presentation.register.RegisterViewModel
import io.github.a2en.rewardzjetpack.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest



@Composable
fun RegisterScreen(
    navController: NavController, viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { screen ->
            navController.navigate(screen.route)
        }
    }
    Box(
        modifier = Modifier
            .background(Color(0xff05152e))
            .fillMaxSize()
    ) {
        buildBgImage()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            Text(
                text = "Q",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier.padding(start = 27.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome to Quantum Wallet!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.padding(start = 27.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Please register in our application to\ncontinue work with your wallets",
                style = TextStyle(
                    color = Color(0xffb3b9c1),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(start = 27.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.weight(1.0f)) // fill height with spacer
            buildRegisterCardWithRoundedCorners(navController, state, viewModel)
        }

    }
}

@Composable
fun buildRegisterCardWithRoundedCorners(
    navController: NavController,
    state: RegisterState,
    viewModel: RegisterViewModel
) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .graphicsLayer {
                        shadowElevation = 10.dp.toPx()
                    }
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    buildUserNameTextField(state, viewModel)
                    Spacer(modifier = Modifier.height(20.dp))
                    buildEmailTextField(state, viewModel)
                    Spacer(modifier = Modifier.height(20.dp))
                    buildRegisterPhoneTextField(state, viewModel)
                    Spacer(modifier = Modifier.height(20.dp))
                    buildRegisterButton(state, viewModel)
                    Spacer(modifier = Modifier.height(30.dp))
                    buildLoginText(navController)
                    Spacer(modifier = Modifier.height(100.dp))

                }
            }
        }
    }
}

@Composable
fun buildLoginText(navController: NavController) {
    Row {
        Text(
            text = "Already have an account?",
            style = TextStyle(
                color = Color(0xffa8c3ec),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.padding(start = 27.dp)
        )
        Text(
            text = " Login",
            style = TextStyle(
                color = Color(0xff5ea7ff),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    navController.navigate(Screen.LoginScreen.route)
                }
        )
    }
}

@Composable
fun buildRegisterButton(state: RegisterState, viewModel: RegisterViewModel) {
    Button(
        onClick = {
            viewModel.onEvent(RegisterEvent.Register)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xff5ea7ff)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5ea7ff))
    ) {
        Text(
            text = "Get Started",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun buildRegisterPhoneTextField(state: RegisterState, viewModel: RegisterViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xfff4f9fe))
        ) {
            OutlinedTextField(
                value = state.phoneNumber,
                leadingIcon = {
                    Text(
                        text = "+01",
                        style = TextStyle(
                            color = Color(0xffa8c3ec),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400
                        ),
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .background(Color(0xfff4f9fe))
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                },
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPhone(it))
                },
                label = { Text(text = "") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xfff4f9fe),
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff4f9fe))


            )
            Text(
                text = "Phone Number",
                style = TextStyle(
                    color = Color(0xffa8c3ec),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            )
        }

        if (state.phoneErrorMsg.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                text = state.phoneErrorMsg,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}


@Composable
fun buildEmailTextField(state: RegisterState, viewModel: RegisterViewModel) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xfff4f9fe))
        ) {
            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredEmail(it))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xfff4f9fe),
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified
                ),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff4f9fe))
            )
            Text(
                text = "Email",
                style = TextStyle(
                    color = Color(0xffa8c3ec),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            )
        }

        if (state.emailErrorMsg.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                text = state.emailErrorMsg,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
fun buildUserNameTextField(state: RegisterState, viewModel: RegisterViewModel) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xfff4f9fe))
        ) {
            OutlinedTextField(
                value = state.userName,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredUsername(it))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xfff4f9fe),
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff4f9fe))


            )
            Text(
                text = "User name",
                style = TextStyle(
                    color = Color(0xffa8c3ec),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            )
        }

        if (state.userNameErrorMsg.isNotBlank()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                text = state.userNameErrorMsg,
                fontSize = 14.sp,
                color = Color.Red
            )
        }
    }
}
