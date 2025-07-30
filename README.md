# Jetpack Compose Android App with MVVM Architecture

This guide walks you through setting up an Android application using **Jetpack Compose** and the **MVVM (Model-View-ViewModel)** architecture pattern.

---

## 📐 MVVM Architecture in Android


MVVM stands for **Model-View-ViewModel** – a design pattern that separates your code into 3 layers:

| Layer     | Responsibility                         |
| --------- | -------------------------------------- |
| Model     | Data layer – Repository, Database, API |
| View      | UI – Screens, Composables              |
| ViewModel | Logic – Connects View and Model        |

MVVM helps to organize code better, test easily, and avoid UI + logic mixing.

🔁 The flow:

```
[Model] ⇄ [ViewModel] ⇄ [View]
```

### MVVM Folder Structure

Using the `com.safari.myfirstapp` package, create this folder structure:

```
com.safari.myfirstapp
│
├── data           # For ViewModels (e.g., AuthViewModel)
│
├── model          # For data models
│
├── navigation     # For app navigation logic and route management
│
└── ui
    └── screens    # For Composable screens (e.g., HomeScreen, LoginScreen)
```

📌 **Note**: `AuthViewModel` will be placed inside the `data` package.

---
## 🧰 Project Setup in Android Studio

```
Open Android Studio

Select Empty Compose Activity

Package name: com.safari.myfirstapp

Finish and wait for Gradle build
```

---

## 📁 Android Project Structure
There are two main views in Android Studio:
1. Android View (default)

    Simplified and categorized (like manifests, java, res)

    Beginner friendly

2. Project View

    Shows actual folder/file structure

    Useful for GitHub uploads or deeper structure understanding

Switch using the dropdown at the top-left of the Project pane.

Important Files

    AndroidManifest.xml: App info, permissions, activities

    res/: Resources like images, strings, themes

    MainActivity.kt: Entry point of the app
    
    Key Project Files
📄 AndroidManifest.xml

    Located under: app/src/main/AndroidManifest.xml

    Declares your app components (Activities, Permissions)

    You’ll add launcher activity, splash screen, etc. here

📁 res/ folder

    drawable/ → Images (e.g., logos)

    layout/ → Not used in Compose (we use Composables instead)

    values/ → Themes, colors, strings

## 📦 What is a Package in Android?

A **package** in Android organizes your code and helps avoid name conflicts. It is essentially a directory structure used to logically group related classes and files.

For example, `com.safari.myfirstapp` means:

- `com` → Top-level domain
- `safari` → Organization name
- `myfirstapp` → Project or app name

To create packages in Android Studio:

1. Right-click `com.safari.myfirstapp`
2. Select **New > Package**
3. Name it (e.g., `data`, `model`, `ui.screens`, etc.)

---

## 💡 What is Jetpack Compose?

Jetpack Compose is Android's modern UI toolkit for building native UI using Kotlin code. It’s fully declarative, meaning you describe how the UI should look and it updates automatically. 
All UI is created in Kotlin functions marked with `@Composable`
Benefits:

    No more XML layouts

    Uses @Composable functions

    Easier to create reusable UI

### Why Compose?

- Less boilerplate
- More intuitive
- Easy to preview

## 🧱 Layouts in Jetpack Compose

A layout organizes UI components on screen.

### Common Layouts

| Layout   | Purpose                             |
| -------- | ----------------------------------- |
| `Column` | Stacks items vertically       |
| `Row`    | Places items side by side      |
| `Box`    | Stack elements on top of each other |
| `LazyColumn` |  Scrollable  vertical list   |

---
### Examples of UI Components
Component	Description	Example

```
Text()	Displays text	Text("Hello")
Button()	Clickable button	Button(onClick = {}) { Text("Click") }
Image()	Shows image from drawable	painterResource(id = R.drawable.logo)
Spacer()	Adds space	Spacer(modifier = Modifier.height(10.dp))
```

## 🎨 Example: DemoScreen

```kotlin
package com.safari.myfirstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safari.myfirstapp.R

@Composable
fun DemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "A COMPANY LOGO",
            modifier = Modifier.height(200.dp)
        )
        Text(text = "Welcome to my app", color = Color.Red, fontSize = 24.sp)
        Text(text = "JETPACK COMPOSE", color = Color.Blue)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {}) { Text("LOGIN") }
            Button(onClick = {}) { Text("REGISTER") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DemoPreview() {
    DemoScreen()
}
```

---
## 🧪 Preview Function

To see a live preview in Android Studio:

```kotlin
@Preview(showBackground = true)
@Composable
fun MyPreview() {
    MyScreen()
}
```

This lets you see your Composable in the design editor.

---

## 🧭 Navigation Setup

use `Navigation-Compose` to manage screen transitions.

### Steps:

1. Add Navigation dependency to `build.gradle`:

```kotlin
dependency {
    implementation("androidx.navigation:navigation-compose:2.5.3")
}
```

2.Create these files under navigation/:

    AppNavHost.kt

Routes.kt


```kotlin
object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
}
```

AppNavHost.kt

Set up `NavHost` in `AppNavHost.kt`:
```kotlin
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) { SplashScreen(navController) }
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.HOME) { HomeScreen(navController) }
    }
}
```

---

## 📷 Diagrams (Folder Structure Example)

```
com.safari.myfirstapp
├── data
│   └── AuthViewModel.kt
├── model
│   └── User.kt
├── navigation
│   └── AppNavHost.kt
└── ui
    └── screens
        ├── LoginScreen.kt
        ├── RegisterScreen.kt
        └── DemoScreen.kt
```



> Created by Faith — Jetpack Compose + MVVM starter project
