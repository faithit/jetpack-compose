package com.faith.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.faith.firstapplication.ui.theme.FrstApplicationTheme
import com.faith.firstapplication.ui.theme.screens.demoscreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrstApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    demoscreen()
                }
            }
        }
    }
}

@Composable
fun demo(){
    Column {
        Text(
            text="welcome to jetpack compose"
        )
        Text(
            text="This a a text field  inside a column"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun demoPreview() {
   demo()
}