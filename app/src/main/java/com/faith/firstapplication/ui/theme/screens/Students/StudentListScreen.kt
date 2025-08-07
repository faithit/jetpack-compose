package com.faith.firstapplication.ui.theme.screens.Students

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faith.firstapplication.data.StudentViewModel
import com.faith.firstapplication.models.Student
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.faith.firstapplication.data.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(navController:NavHostController ) {
    val context = LocalContext.current
    val viewModel =StudentViewModel(navController, context)

    val student = remember { mutableStateOf(Student("", "", "", "", "")) }
    val students = remember { mutableStateListOf<Student>() }

    // Fetch students from Firebase
    LaunchedEffect(Unit) {
        viewModel.allStudents(student, students)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Student List") })
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(students) { studentItem ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Name: ${studentItem.name}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Age: ${studentItem.age}")
                        Text(text = "Course: ${studentItem.course}")
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            // Delete Button
                            Text(
                                text = "Delete",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                      viewModel.deleteStudent(studentItem.id)
                                    }
                            )

                            // Update Button
                            Text(
                                text = "Update",
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        // Navigate to Update Screen (pass student data or ID)
//                                        navController.navigate("update_student/${studentItem.id}")
                                    }
                            )
                    }
                }
            }
        }
    }
}
}
