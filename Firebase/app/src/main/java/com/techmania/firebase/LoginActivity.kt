package com.techmania.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.techmania.firebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.buttonSignin.setOnClickListener {

            val userEmail = loginBinding.editTextEmailSignin.text.toString()
            val userPassword = loginBinding.editTextPasswordSignin.text.toString()

            signinWithFirebase(userEmail, userPassword)

        }

        loginBinding.buttonSignup.setOnClickListener {

            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)

        }

        loginBinding.buttonForgot.setOnClickListener {

            val intent = Intent(this,ForgetActivity::class.java)
            startActivity(intent)

        }

        loginBinding.buttonSigninWithPhoneNumber.setOnClickListener {

            val intent = Intent(this,PhoneActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun signinWithFirebase(userEmail : String, userPassword : String){

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(applicationContext,"Login is successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null){

            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

}