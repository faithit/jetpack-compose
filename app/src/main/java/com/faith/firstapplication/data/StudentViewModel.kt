package com.faith.firstapplication.data

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.faith.firstapplication.models.Student
import com.faith.firstapplication.navigation.ROUTE_LOGIN


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StudentViewModel(var navController: NavHostController, var context: Context) {

    var authViewModel: AuthViewModel
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Students")

    init {
        authViewModel = AuthViewModel(navController, context)
//        if (!authViewModel.isLoggedIn()) {
//            //navController.navigate(ROUTE_LOGIN)
//        }
    }
    fun uploadStudent(name: String, age: String, course: String) {
        val studentId = System.currentTimeMillis().toString()
        val studentRef = FirebaseDatabase.getInstance().getReference()
            .child("Students/$studentId")

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val studentData = Student(name = name, age = age, course = course, id = studentId, userId = userId)

        studentRef.setValue(studentData).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Student added successfully",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error: ${it.exception?.message}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    // Fetch all students
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
                Toast.makeText(context, "Database error", Toast.LENGTH_SHORT).show()
            }
        })

        return students
    }
    // DELETE
    fun deleteStudent(studentId: String) {
        databaseReference.child(studentId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Student deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
            }
    }

}