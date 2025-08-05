package com.faith.firstapplication.ui.theme.screens.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.R
import com.faith.firstapplication.data.AuthViewModel
import com.faith.firstapplication.navigation.ROUTE_LOGIN
import com.faith.firstapplication.ui.theme.MintGreen
import com.faith.firstapplication.ui.theme.SoftYellow

@Composable
fun RegisterScreen(navController:NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
         var fullname by remember { mutableStateOf(" ")}
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmpass by remember { mutableStateOf("") }
        Text(
            text="REGISTER",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color= MintGreen,
            fontFamily = FontFamily.SansSerif

        )
        Image(
            painter= painterResource(id= R.drawable.logo2),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)

        )
        Text(
            text="Dont have an account? register here",
            color=Color.Magenta,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value =fullname,
            onValueChange={fullname = it}, //updates fullname as user types
            label={ Text(text="Fullname")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person icon"
                )
            }
            )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value =email,
            onValueChange={email = it}, //updates fullname as user types
            label={ Text(text="Email Address")},
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
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value =password,
            onValueChange={password = it}, //updates password as user types
            label={ Text(text="Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "LOCK icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value =confirmpass,
            onValueChange={confirmpass = it}, //updates fullname as user types
            label={ Text(text=" Confirm Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "LOCK icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(onClick = {
            authViewModel.signup(fullname, email, password,confirmpass)
        },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            colors=ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Magenta
            )
        ) {
            Text(text = "REGISTER",
                fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = {navController.navigate(ROUTE_LOGIN)}) {
            Text(
                text = "Already have an account?Login",
                fontSize = 24.sp,
                color = Color.Magenta
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun registerpreview(){
    RegisterScreen(rememberNavController())
}

