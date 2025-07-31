package com.faith.firstapplication.ui.theme.screens.demo


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faith.firstapplication.R

@Composable
fun demoscreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter=painterResource(id= R.drawable.logo),
            contentDescription = "A COMPANY  LOGO",
            modifier = Modifier
                .height(200.dp),
        )
        Text(
            text="welcome to my app",
            color =Color.Red,
            fontSize = 24.sp
        )
        Text(
            text="JETPACK COMPOSE",
            color=Color.Blue,
        )
        Spacer(modifier =   Modifier.height(24.dp))
        //row with two BUTTONS
        Row(
            modifier=Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
                Button(onClick = {}) {
                    Text("lOGIN")
                }
            Button(onClick = {}) {
                Text("REGISTER")
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun demopreview(){
    demoscreen()
}

