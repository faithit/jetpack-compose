package com.faith.firstapplication.ui.theme.screens.Students

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.data.StudentViewModel
import com.faith.firstapplication.models.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStudentScreen(navController: NavHostController, studentId: String) {
    val context = LocalContext.current
    val viewModel = StudentViewModel(navController, context)

    // State for student data
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val course = remember { mutableStateOf("") }

    // Fetch student from Firebase by ID
    LaunchedEffect(studentId) {
        viewModel.databaseReference.child(studentId).get().addOnSuccessListener { snapshot ->
    //convert the retrieved snapshot into a Student Object
            val student = snapshot.getValue(Student::class.java)
            //update the ui state variables
            student?.let {
                name.value = it.name
                age.value = it.age
                course.value = it.course
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Update Student") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize()
        ) {
            Text(text = "Name")
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Age")
            TextField(
                value = age.value,
                onValueChange = { age.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Course")
            TextField(
                value = course.value,
                onValueChange = { course.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val updatedStudent = Student(
                        id = studentId,
                        name = name.value,
                        age = age.value,
                        course = course.value
                    )
                    viewModel.updateStudent(updatedStudent)
                    navController.popBackStack() // Go back to list
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Student")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun updatepreview() {
    UpdateStudentScreen(rememberNavController(), studentId = "")
}