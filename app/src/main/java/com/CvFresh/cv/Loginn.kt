package com.CvFresh.cv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.CvFresh.cv.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.CvFresh.cv.Loginn
import android.widget.TextView

class Loginn : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val tvLogin = binding.textViewLogin.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textViewLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val pwd = binding.etPassword.text.toString()


            if (email.isNotEmpty() && pwd.isNotEmpty() && pwd.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


