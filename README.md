# Jetpack Compose Android App with MVVM Architecture

This guide walks you through setting up an Android application using **Jetpack Compose** and the **MVVM (Model-View-ViewModel)** architecture pattern.

---

## 📐 MVVM Architecture in Android

**MVVM (Model-View-ViewModel)** is a design architecture that helps separate concerns and promotes clean, maintainable code.

- **Model**: Manages the data and business logic (e.g., API, database).
- **View**: Displays UI components and collects user input (Compose UI).
- **ViewModel**: Acts as a bridge between View and Model. Holds UI-related data and state.

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

## 📦 What is a Package in Android?

A **package** in Android organizes your code and helps avoid name conflicts. It is essentially a directory structure used to logically group related classes and files.

For example, `com.safari.myfirstapp` means:

- `com` → Top-level domain
- `safari` → Organization name
- `myfirstapp` → Project or app name

To create packages in Android Studio:

1. Right-click `java/com.safari.myfirstapp`
2. Select **New > Package**
3. Name it (e.g., `data`, `model`, `ui.screens`, etc.)

---

## 💡 What is Jetpack Compose?

Jetpack Compose is Android's modern UI toolkit for building native UI using Kotlin code. It’s fully declarative, meaning you describe how the UI should look and it updates automatically.

### Why Compose?

- Less boilerplate
- More intuitive
- Easy to preview

---

## 🧱 Layouts in Jetpack Compose

Jetpack Compose replaces XML with composable functions.

### Common Layouts

| Layout   | Purpose                             |
| -------- | ----------------------------------- |
| `Column` | Arrange children vertically         |
| `Row`    | Arrange children horizontally       |
| `Box`    | Stack elements on top of each other |
| `Spacer` | Adds spacing between components     |

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

## 🧭 Navigation Setup

Use `Navigation-Compose` to manage screen transitions.

### Steps:

1. Add Navigation dependency to `build.gradle`:

```kotlin
dependency {
    implementation("androidx.navigation:navigation-compose:2.5.3")
}
```

2. Create NavHost:

```kotlin
NavHost(
    navController = navController,
    startDestination = "splash"
) {
    composable("splash") { SplashScreen(navController) }
    composable("login") { LoginScreen(navController) }
    composable("register") { RegisterScreen(navController) }
    composable("home") { HomeScreen() }
}
```

3. Define routes and navigation logic in the `navigation` package.

---

## 🗂️ Android Project Structure

Switch between:

- **Project** view: file-based structure
- **Android** view: module-based, easier to work with

Folders to know:

- `manifests/AndroidManifest.xml` → App configuration
- `java/com.safari.myfirstapp` → Main source code
- `res/` → Resources like layout, images, strings

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
