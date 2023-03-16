package com.CvFresh.cv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.CvFresh.cv.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.newFixedThreadPoolContext

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textViewLogin.setOnClickListener{
            val intent = Intent(this, Loginn::class.java)
            startActivity(intent)
      }
        binding.btnSignUp.setOnClickListener{
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val email = binding.etEmail.text.toString()
            val pwd = binding.etPassword.text.toString()
            val pwdConfirm = binding.etConfirmPassword.text.toString()

            if(name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty() && pwd.isNotEmpty() && pwdConfirm.isNotEmpty()) {

                if(pwd == pwdConfirm){
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this, Loginn::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                   Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }

        }



    }

}