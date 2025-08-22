
package com.faith.firstapplication.ui.theme.screens.Students

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.faith.firstapplication.data.StudentViewModel
import com.faith.firstapplication.models.Student
import com.faith.firstapplication.navigation.ROUTE_lISTSTUDENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStudentScreen(
    navController: NavHostController,
    studentId: String
) {
    val context = LocalContext.current
    val studentViewModel = StudentViewModel(navController, context)

    // States for student data
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Fetch student from Firebase
    LaunchedEffect(studentId) {
        studentViewModel.databaseReference.child(studentId).get().addOnSuccessListener { snapshot ->
            val student = snapshot.getValue(Student::class.java)
            student?.let {
                it.id = studentId
                name = it.name
                age = it.age
                course = it.course
                imageUrl = it.imageUrl // load existing image if present
            }
        }
    }

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Update Student", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2196F3))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = course,
                onValueChange = { course = it },
                label = { Text("Course") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Show selected or existing image
            val imageSource: Any? = imageUri ?: imageUrl
            if (imageSource != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageSource),
                    contentDescription = "Student Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text("Change Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    studentViewModel.updateStudent(
                        studentId,
                        name = name,
                        age = age,
                        course = course,
                        imageUri = imageUri ,

                        // pass old one if no new image
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Student")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun UpdateStudentPreview() {
//    UpdateStudentScreen(rememberNavController(), studentId = "")
//}
