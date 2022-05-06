package com.adithyaegc.ordermanagementsystem.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.adithyaegc.ordermanagementsystem.databinding.ActivityLoginBinding
import com.adithyaegc.ordermanagementsystem.util.Constants.Companion.EMAIL
import com.adithyaegc.ordermanagementsystem.util.Constants.Companion.PASSWORD
import com.adithyaegc.ordermanagementsystem.util.Constants.Companion.SHARED_PREF_NAME

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {

            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                userLogin()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Fields can't be empty.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun userLogin() {

        val sharedPreference = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        editor.putBoolean("isLoggedIn", true)
        editor.putString(EMAIL, binding.etEmailLogin.text.toString())
        editor.putString(PASSWORD, binding.etPasswordLogin.text.toString())

        editor.apply()
    }


    //check user already logged in
    override fun onStart() {
        super.onStart()
        val sp = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        if (sp.contains("isLoggedIn")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish() //finish current activity
        }
    }
}