// Material3 version of imports
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Notifications

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val navItems = listOf("Home", "Courses", "Notifications")
    val navIcons = listOf(Icons.Default.Home, Icons.Default.Person, Icons.Default.Notifications)
    var selectedIndex by remember { mutableStateOf(0) }

    val cardList = listOf(
        DashboardCard("Analytics", "View stats and insights", Color(0xFFBBDEFB)),
        DashboardCard("Messages", "Check new messages", Color(0xFFC8E6C9)),
        DashboardCard("Tasks", "Manage your to-dos", Color(0xFFFFF9C4)),
        DashboardCard("Settings", "Update preferences", Color(0xFFFFCDD2))
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EE), titleContentColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1976D2), // Blue background for BottomNav
                contentColor = Color.White ) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(navIcons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cardList) { card ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clickable {
                            println("${card.title} clicked!")
                        },
                    colors = CardDefaults.cardColors(containerColor = card.color),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = card.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = card.description,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

data class DashboardCard(
    val title: String,
    val description: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}
