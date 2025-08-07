import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faith.firstapplication.data.AuthViewModel
import com.faith.firstapplication.data.StudentViewModel
import androidx.compose.ui.platform.LocalContext
import com.faith.firstapplication.navigation.ROUTE_ADDSTUDENT
import com.faith.firstapplication.navigation.ROUTE_HOME
import com.faith.firstapplication.navigation.ROUTE_lISTSTUDENT

//create a screen lets say overviwscreen
//add topbar
//add a image//
//add  a welcome text
//add card(title,icon)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(navController: NavHostController) {
    val context = LocalContext.current
    val authViewModel = AuthViewModel(navController, context)
    val navItems= listOf("Home","Add Students","Grades","notifications")
    val navIcons= listOf(Icons.Default.Home,Icons.Default.Person,Icons.Default.Menu,Icons.Default.Notifications)
    val navRoutes = listOf(ROUTE_HOME, ROUTE_ADDSTUDENT, ROUTE_lISTSTUDENT, ROUTE_lISTSTUDENT)
    var selectedIndex by remember { mutableStateOf(0)}
    Scaffold(

        //topbar
        topBar = {
            TopAppBar(
                title = { Text("ADD STUDENT") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "search icon"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "person icon"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "settings icon"
                        )
                    }
                    TextButton(onClick = {
                        authViewModel.logout()


                    }) {
                        Text(
                            text = "Logout",
                            color = Color.White
                        )
                    }

                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Magenta,
                contentColor = Color.White) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(navIcons[index], contentDescription = item) },
                        label = { Text(item, color = Color.White) },
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(navRoutes[index]) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }


    )
    { innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()

                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            var name by remember { mutableStateOf("") }
            var age by remember { mutableStateOf("") }
            var course by remember { mutableStateOf("") }
            val context = LocalContext.current
            val studentviewmodel = StudentViewModel(navController, context)
            Text(
                text = "Add STUDENT",
                color = Color.Magenta,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter STUDENT NAME") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Enter STUDENT Age") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = course,
                onValueChange = { course = it },
                label = { Text("Enter course") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {

                studentviewmodel.uploadStudent(
                    name = name,
                    age = age,
                    course = course
                )

                //  clear form
                name = ""
                age = ""
                course = ""

            },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "ADD STUDENT")
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun addstudentprevie(){
    AddStudentScreen(rememberNavController())
}
