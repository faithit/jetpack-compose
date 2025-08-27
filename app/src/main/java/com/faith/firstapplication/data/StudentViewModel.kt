package com.faith.firstapplication.data
import androidx.navigation.NavHostController
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.faith.firstapplication.models.Student

import com.faith.firstapplication.navigation.ROUTE_lISTSTUDENT


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import okhttp3.Request


import android.net.Uri

import androidx.compose.runtime.mutableStateListOf


import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient

import okhttp3.RequestBody
import java.io.InputStream

class StudentViewModel(
    var navController: NavHostController, var context: Context
)  {

    val  databaseReference = FirebaseDatabase.getInstance().getReference("Students")

    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/dx5oluh2i/image/upload" //change the cloud name
    val uploadPreset = "students"

    val _students = mutableStateListOf<Student>()
    val students: List<Student> = _students

    // -------- Upload Student --------
    fun uploadStudent(
        imageUri: Uri?,
        name: String,
        age: String,
        course: String
    ) {
        val ref = FirebaseDatabase.getInstance().getReference("Students").push()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val imageUrl = if (imageUri != null) {
                    uploadToCloudinary(context, imageUri) // returns secureUrl
                } else {
                    ""
                }

                val studentData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "age" to age,
                    "course" to course,
                    "userId" to userId,
                    "imageUrl" to imageUrl
                )

                ref.setValue(studentData).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Student saved successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_lISTSTUDENT)
                    } else {
                        Toast.makeText(context, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    // -------- Fetch Students --------
    fun allStudents(
        student: MutableState<Student>,
        students: SnapshotStateList<Student>
    ): SnapshotStateList<Student> {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                students.clear()
                for (snap in snapshot.children) {
                    val retrievedStudent = snap.getValue(Student::class.java)
                    if (retrievedStudent != null) {
                        student.value = retrievedStudent
                        students.add(retrievedStudent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database error",
                    Toast.LENGTH_SHORT).show()
            }
        })

        return students
    }
    // -------- Delete Student --------
    fun deleteStudent(studentId: String) {
        databaseReference.child(studentId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Student deleted",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to delete",
                    Toast.LENGTH_SHORT).show()
            }
    }

fun updateStudent(
    studentId: String,
    name: String,
    age: String,
    course: String,
    imageUri: Uri?,

) {

    CoroutineScope(Dispatchers.IO).launch {
        try {
            // upload image only if selected
            val newImageUrl = imageUri?.let { uploadToCloudinary(context, it) }

            val currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser?.uid ?: ""

            val updates = mutableMapOf<String, Any>(
                "id" to studentId,
                "name" to name,
                "age" to age,
                "course" to course,
                "userId" to userId
            )

            if (!newImageUrl.isNullOrEmpty()) {
                updates["imageUrl"] = newImageUrl
            }

            val ref = databaseReference.child(studentId)
            ref.updateChildren(updates).await() // suspend until done

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Student updated successfully", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_lISTSTUDENT)
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}


    // : Upload to Cloudinary --------
    private fun uploadToCloudinary(context:Context,uri: Uri):String{
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes() ?: throw  Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file","image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(),fileBytes))
            .addFormDataPart("upload_preset",uploadPreset).build()
        val request = Request.Builder().url(cloudinaryUrl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if(!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image URL")
    }

}
