package com.techmania.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.techmania.firebase.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        signupBinding.buttonSignupUser.setOnClickListener {

            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()

            signupWithFirebase(userEmail, userPassword)

        }

    }

    fun signupWithFirebase(userEmail : String, userPassword : String){

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful){

                Toast.makeText(applicationContext,"Your account has been created",Toast.LENGTH_SHORT).show()
                finish()

            }else{

                Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()

            }

        }

    }

}