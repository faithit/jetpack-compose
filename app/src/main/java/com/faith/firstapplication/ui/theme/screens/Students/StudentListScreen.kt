package com.faith.firstapplication.ui.theme.screens.Students

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.faith.firstapplication.data.AuthViewModel
import com.faith.firstapplication.navigation.ROUTE_UPDATESTUDENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(navController:NavHostController ) {
    val context = LocalContext.current

    val studentviewmodel = StudentViewModel(navController, context)


    val student = remember { mutableStateOf(Student("", "", "", "", "")) }
    val students = remember { mutableStateListOf<Student>() }

    // Fetch students from Firebase
    LaunchedEffect(Unit) {
        studentviewmodel.allStudents(student, students)
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
                        // Product Image add this
                        Image(
                            painter = rememberAsyncImagePainter(studentItem.imageUrl),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(bottom = 8.dp),
                            contentScale = ContentScale.Crop
                        )

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
                                     studentviewmodel.deleteStudent(studentItem.id)
                                    }
                            )

                            // Update Button
                            Text(
                                text = "Update",
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        navController.navigate("$ROUTE_UPDATESTUDENT/${studentItem.id}")
                                    }
                            )
                    }

                }
            }
        }
    }
}
}
@Preview
@Composable
fun viewpreview() {
    StudentListScreen(rememberNavController())

}