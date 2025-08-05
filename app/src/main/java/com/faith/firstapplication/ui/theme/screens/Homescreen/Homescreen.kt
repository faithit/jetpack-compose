package com.faith.firstapplication.ui.theme.screens.Homescreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.navigation.ROUTE_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController){
    val navItems= listOf("Home","Add Students","Grades","notifications")
    val navIcons= listOf(Icons.Default.Home,Icons.Default.Person,Icons.Default.Menu,Icons.Default.Notifications)
    var selectedIndex by remember { mutableStateOf(0)}
    Scaffold(
        //topbar
        topBar = {
            TopAppBar(
                title = { Text("Homescreen")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search,
                            contentDescription = "search icon")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person,
                            contentDescription = "person icon")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings,
                            contentDescription = "settings icon")
                    }
                }
            )
        },
        //BOTTOMBAR
        bottomBar = {
            NavigationBar(
                containerColor = Color.Magenta,
                contentColor = Color.White) {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(navIcons[index], contentDescription = item) },
                            label = { Text(item, color = Color.White) },
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index }
                        )
                }
            }
        }

    ) { innerpadding->
        Column(
            modifier = Modifier
               .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            HomeCard(
               title = "Add STUDENT",
                description = "click to add a new student",
                backgroundColor = Color(0xff2196f3),
                onclick = {navController.navigate(ROUTE_LOGIN)}

            )
            HomeCard(
                title = "View STUDENTs",
                description = "see all registered students",
                backgroundColor = Color(0xff4caf50),
                onclick = {}

            )
            HomeCard(
                title="Update Student",
                description = "modify existing student",
                backgroundColor = Color(0XFFFF9800),
                onclick = {}
            )
            HomeCard(
                title="DELETE Student",
                description = "Remove an existing student",
                backgroundColor = Color(0XFFF44336),
                onclick = {}
            )
        }

    }

}
@Composable
fun HomeCard(
    title:String,
    description:String,
    backgroundColor: Color,
    onclick:()->Unit
){
    Card(
        modifier=Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable{onclick()},
        shape=RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text( text =title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            Text( text =description,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun homescreenpreview(){
    HomeScreen(rememberNavController())
}