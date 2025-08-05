package com.faith.firstapplication.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.data.AuthViewModel
import com.faith.firstapplication.navigation.ROUTE_REGISTER
import com.faith.firstapplication.navigation.ROUTE_HOME
import com.faith.firstapplication.ui.theme.MediumOrchid
import com.faith.firstapplication.ui.theme.SoftBlue
import com.faith.firstapplication.ui.theme.SoftPink


@Composable
fun LoginScreen(navController: NavHostController){
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("")}
        Text(
            text="LOGIN",
            fontSize = 40.sp,
            color= MediumOrchid,
            fontFamily = FontFamily.Cursive
            )
        Spacer(modifier = Modifier.height((20.dp)))
        Text(
            text="Already have an acount?Login here",
            color=Color.Magenta,
            fontSize = 20.sp
            )
        Spacer(modifier = Modifier.height((20.dp)))
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = {Text("Email Address")},
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Magenta,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "email icon"
                )
            }

        )
        Spacer(modifier = Modifier.height((20.dp)))
        OutlinedTextField(
            value = password,
            onValueChange = {password=it},
            label = {Text("Password")},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "password icom"
                )
            }

        )
        Spacer(modifier = Modifier.height((20.dp)))
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(onClick = {
            authViewModel.login(email, password)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Yellow
            )
        ) {
            Text("LOGIN",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height((20.dp)))
       TextButton(onClick = {navController.navigate(ROUTE_REGISTER)}) {
           Text("Dont have an account?Register",
               fontSize = 20.sp,
               color=Color.Magenta)
       }

    }
}
@Preview(showBackground = true)
@Composable
fun loginpreview(){
    LoginScreen(rememberNavController())
}