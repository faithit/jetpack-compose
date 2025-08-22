package com.faith.firstapplication.ui.theme.screens.intent

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Reusable Intent Button
@Composable
fun IntentButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(label, color = Color.White)
    }
}

@Composable
fun IntentScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Intents Demo",
            color = Color.Blue,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Camera
        IntentButton("Open Camera") {
            //CREATE INTENT
            //val intent=Intent()
            //startactivity(Intent)
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(context as Activity, cameraIntent, 1, null)
        }

        // 📞 Direct Call (with permission check)
        IntentButton("📞 Direct Call") {
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:+25472633522"))
            if (ContextCompat.checkSelfPermission(
                    context, android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                context.startActivity(callIntent)
            }
        }

        // 💬 SMS
        IntentButton(" Send SMS") {
            val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:0729618566"))
            smsIntent.putExtra("sms_body", "Hello this is  mesaage from intentn in android?")
            context.startActivity(smsIntent)
        }

        // SIM Toolkit
        IntentButton(" Open SIM Toolkit") {
            context.packageManager.getLaunchIntentForPackage("com.android.stk")
                ?.let { context.startActivity(it) }
        }

        // 📤 Share Text
        IntentButton("📤 Share Text") {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, check out my app! https://play/...")
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        // 📧 Email
        IntentButton("📧 Send Email") {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "faithit@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        // 📲 Dialer
        IntentButton("📲 Open Dialer") {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0729618566"))
            context.startActivity(dialIntent)
        }

        //  Website
        IntentButton("Open Website") {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emobilis.com"))
            context.startActivity(webIntent)
        }

        // 🗺 Google Maps
        IntentButton("🗺 Open Google Maps") {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Emobilis+Nairobi,Kenya"))
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        }
        //open gallery
        IntentButton("OPEN GALLERY") {
             val galleryintent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(context as Activity,galleryintent,2,null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntentPreview() {
    IntentScreen(rememberNavController())
}
