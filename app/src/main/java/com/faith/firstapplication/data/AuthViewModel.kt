package com.faith.firstapplication.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.faith.firstapplication.models.User
import com.faith.firstapplication.navigation.ROUTE_HOME
import com.faith.firstapplication.navigation.ROUTE_LOGIN
import com.faith.firstapplication.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel(var navController: NavHostController, var context: Context) {

    var mAuth: FirebaseAuth
    init {
        mAuth = FirebaseAuth.getInstance()

    }
    fun signup(fullname:String,email: String, pass: String,confirmpass: String) {

        if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(
                context, "Please email and password cannot be" +
                        " blank", Toast.LENGTH_LONG
            ).show()
            return
        } else if (pass !=confirmpass) {
            Toast.makeText(
                context, "Password do not match",
                Toast.LENGTH_LONG
            ).show()
            return
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata = User(fullname,email, pass, mAuth.currentUser!!.uid)
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {

                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Registered Successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)

                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            }
                        }
                    } else {
                        navController.navigate(ROUTE_REGISTER)
                    }

                }
        }

    }
    fun login(email: String,pass: String){
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful ){
                        Toast.makeText(this.context, "Successfully loggined in",
                            Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_HOME)
                    }else{
                        Toast.makeText(this.context, "Error logging in",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN){
        popUpTo(0)}
    }

    fun isLoggedIn(): Boolean = mAuth.currentUser != null
}