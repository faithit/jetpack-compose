package com.faith.firstapplication.ui.theme.screens.Splashscreen

import androidx.navigation.NavHostController




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.R
import com.faith.firstapplication.navigation.ROUTE_LOGIN
import com.faith.firstapplication.navigation.ROUTE_ONBOARDING
import com.faith.firstapplication.navigation.ROUTE_SPLASH

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(true) {
        delay(2000) // 2-second splash duration
        navController.navigate(ROUTE_ONBOARDING) {
            popUpTo(ROUTE_SPLASH) { inclusive = true } // Remove splash from backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.school),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to my app",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun splashpreview(){
//    SplashScreen(rememberNavController())
//}