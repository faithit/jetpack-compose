//package com.faith.firstapplication.ui.theme.screens.demo
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.faith.firstapplication.data.StudentViewModel
//
//import com.faith.firstapplication.models.Student
//import com.faith.firstapplication.navigation.ROUTE_UPDATESTUDENT
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StudentListScreen(navController: NavHostController) {
//    val context= LocalContext.current
//    val viewmodel=StudentViewModel(navController,context)
//
//    val student= remember { mutableStateOf(Student("","","","")) }
//    val students= remember { mutableStateListOf<Student>() }
//    //FETCH STUDENTS FROM FIREBASE
//    LaunchedEffect(Unit) {
//        viewmodel.allStudents(student,students)
//
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Student List") })
//        }
//    ) { paddingValues ->
//        LazyColumn(
//            contentPadding = paddingValues,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            items(students) { student ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 8.dp),
//                    elevation = CardDefaults.cardElevation(4.dp)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(text = "Name: ${student.name}", style = MaterialTheme.typography.titleMedium)
//                        Text(text = "Age: ${student.age}")
//                        Text(text = "Course: ${student.course}")
//                    }
//                    Row(modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween) {
//                        // Delete Button
//                        Text(
//                            text = "Delete",
//                            color = MaterialTheme.colorScheme.error,
//                            modifier = Modifier
//                                .padding(8.dp)
//                                .clickable {
//                                    viewmodel.deleteStudent(student.id)
//                                }
//                        )
//                        //UPDATE BUTTON
//                            Text(
//                                text = "UPDATE",
//                                color = MaterialTheme.colorScheme.error,
//                                modifier = Modifier
//                                    .padding(8.dp)
//                                    .clickable {
//                                        navController.navigate("ROUTE_UPDATESTUDENT/${student.id}")
//                                    }
//                            )
//
//                }
//            }
//        }
//    }
//}}
//@Preview
//@Composable
//fun studentlistpreview() {
//StudentListScreen(rememberNavController())
//
//}