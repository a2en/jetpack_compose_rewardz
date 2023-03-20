package io.github.a2en.rewardzjetpack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.a2en.rewardzjetpack.R
import io.github.a2en.rewardzjetpack.presentation.login.LoginEvent
import io.github.a2en.rewardzjetpack.presentation.login.LoginState
import io.github.a2en.rewardzjetpack.presentation.login.LoginViewModel

@Composable
fun LoginScreen(onNavigateToSignIn: () -> Unit,viewModel: LoginViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .background(Color(0xff05152e))
            .fillMaxSize()
    ) {
        buildBgImage()
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
        ){
            Text(text = "Q", style = TextStyle(color = Color.White, fontSize = 50.sp, fontWeight = FontWeight.W500, fontFamily = FontFamily.SansSerif), modifier = Modifier.padding(start = 27.dp))
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Welcome!", style = TextStyle(color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 27.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Please enter your phone\nnumber to continué", style = TextStyle(color = Color(0xffb3b9c1), fontSize = 16.sp, fontWeight = FontWeight.W400), modifier = Modifier.padding(start = 27.dp))
            Spacer(modifier = Modifier.height(60.dp))
            Spacer(modifier = Modifier.weight(1.0f)) // fill height with spacer
            buildCardWithRoundedCorners(onNavigateToSignIn,state,viewModel)
        }

    }
}

@Composable
fun buildCardWithRoundedCorners(
    onNavigateToSignIn: () -> Unit,
    state: LoginState,
    viewModel: LoginViewModel
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp,))
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
            buildPhoneTextField(state,viewModel)
            Spacer(modifier = Modifier.height(30.dp))
            buildLoginButton(viewModel)
            Spacer(modifier = Modifier.height(30.dp))
            buildRegisterText(onNavigateToSignIn)
            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}

@Composable
fun buildRegisterText(onNavigateToSignIn: () -> Unit) {
    Row {
        Text(
            text = "Don't have an account?",
            style = TextStyle(
                color = Color(0xffa8c3ec),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.padding(start = 27.dp)
        )
        Text(
            text = " Register",
            style = TextStyle(
                color = Color(0xff5ea7ff),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable {
                    onNavigateToSignIn()
                }
        )
    }
}

@Composable
fun buildLoginButton(viewModel: LoginViewModel) {
    Button(
        onClick = {
                  viewModel.onEvent(LoginEvent.Login)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xff5ea7ff)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff5ea7ff))
    ) {
        Text(text = "Login", style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.W500))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun buildPhoneTextField(state: LoginState, viewModel: LoginViewModel) {

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
                                viewModel.onEvent(LoginEvent.EnteredPhone(it))
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
fun buildBgImage() {
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.2f)
            .background(Color.Black)

    )
}

