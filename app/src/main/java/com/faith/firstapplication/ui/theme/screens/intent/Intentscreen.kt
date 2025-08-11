package com.faith.firstapplication.ui.theme.screens.intent



import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Reusable button composable
@Composable
fun StyledButton(text: String, action: () -> Unit) {
    OutlinedButton(
        onClick = action,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(55.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
    ) {
        Text(text = text, fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun IntentScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101820)) // Dark background
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "📱 Android Intents Demo",
            color = Color(0xFFFFD700),
            fontFamily = FontFamily.Cursive,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // CAMERA
        StyledButton("📸 Open Camera") {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(context as Activity, takePictureIntent, 1, null)
        }

        // CALL
        StyledButton("📞 Direct Call") {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:+25472633522"))
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                context.startActivity(intent)
            }
        }

        // SMS
        StyledButton("💬 Send SMS") {
            val uri = Uri.parse("smsto:07456789")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "How is today's weather?")
            context.startActivity(intent)
        }

        // SIM TOOLKIT
        StyledButton("💳 Open SIM Toolkit") {
            val simToolKitLaunchIntent =
                context.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { context.startActivity(it) }
        }

        // SHARE
        StyledButton("📤 Share Text") {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        // EMAIL
        StyledButton("📧 Send Email") {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "abc@gmail.com", null)
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        // DIAL
        StyledButton("📲 Open Dialer") {
            val phone = "+34666777888"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            context.startActivity(intent)
        }

        // NEW → OPEN WEBSITE
        StyledButton("🌐 Open Website") {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            context.startActivity(browserIntent)
        }

        // NEW → OPEN GOOGLE MAPS
        StyledButton("🗺 Open Google Maps") {
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=Nairobi,Kenya")
            )
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        }
    }
}

@Composable
fun IntentPreview() {
    IntentScreen(rememberNavController())
}
