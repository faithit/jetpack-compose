package com.faith.firstapplication.ui.theme.screens.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun DemoHomeScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Navigation Menu", modifier = Modifier.padding(16.dp))
                Divider()
                Text("Home", modifier = Modifier.padding(16.dp))
                Text("Settings", modifier = Modifier.padding(16.dp))
                Text("Logout", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                HomeTopAppBar(
                    onDrawerClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                    onSearchClick = { /* handle search */ },
                    onSettingsClick = { /* handle settings */ },
                    onProfileClick = { /* handle profile */ }
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Welcome to the Home Screen")
                }
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun demohomepreview(){
    DemoHomeScreen()
}
