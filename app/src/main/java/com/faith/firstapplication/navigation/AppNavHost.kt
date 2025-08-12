package com.faith.firstapplication.navigation

import AddStudentScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.ui.theme.screens.Homescreen.HomeScreen
import com.faith.firstapplication.ui.theme.screens.Splashscreen.SplashScreen
import com.faith.firstapplication.ui.theme.screens.Students.StudentListScreen
import com.faith.firstapplication.ui.theme.screens.Students.UpdateStudentScreen
import com.faith.firstapplication.ui.theme.screens.intent.IntentScreen
import com.faith.firstapplication.ui.theme.screens.login.LoginScreen
import com.faith.firstapplication.ui.theme.screens.register.RegisterScreen

@Composable
fun  AppNavHost(
    modifier: Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination:String= ROUTE_SPLASH
){
    NavHost(
        navController=navController,
        modifier=modifier,
        startDestination=startDestination)
    {
            composable(ROUTE_SPLASH){
              SplashScreen(navController)
            }
            composable(ROUTE_REGISTER){
                RegisterScreen(navController)
            }
            composable(ROUTE_LOGIN){
                LoginScreen(navController)
            }
            composable(ROUTE_HOME) {
                HomeScreen(navController)
            }
            composable(ROUTE_ADDSTUDENT) {
                AddStudentScreen(navController)
            }
            composable(ROUTE_lISTSTUDENT) {
            StudentListScreen(navController)
            }
            composable(ROUTE_UPDATESTUDENT + "/{studentId}") { backStackEntry ->
                val studentId = backStackEntry.arguments?.getString("studentId")!!
                UpdateStudentScreen(navController, studentId)
            }
        composable(ROUTE_MYINTENTS) {
            IntentScreen(navController)
        }

    }

}