package com.grandstream.playandroid.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.grandstream.playandroid.ui.theme.CustomColor
import com.grandstream.playandroid.viewmodel.RegisterViewModel
import com.grandstream.playandroid.widget.PageLoading
import com.grandstream.playandroid.widget.TitleBar

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = viewModel()
    PageLoading(showLoading = viewModel.showLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleBar(title = "注册", onBack = {navController.popBackStack()})
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(140.dp))
                OutlinedTextField(
                    value = viewModel.username,
                    onValueChange = { viewModel.username = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "请输入用户名", color = CustomColor.green, fontSize = 14.sp)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "请输入密码", color = CustomColor.green, fontSize = 14.sp)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = viewModel.repassword,
                    onValueChange = { viewModel.repassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "请确认密码", color = CustomColor.green, fontSize = 14.sp)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(34.dp))
                Button(
                    onClick = { viewModel.register(navController) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 14.dp),
                    shape = RoundedCornerShape(percent = 18),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColor.green,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "注册", fontSize = 14.sp)
                }
            }
        }
    }
}