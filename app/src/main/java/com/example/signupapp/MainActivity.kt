package com.example.signupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.signupapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var signUpResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        signUpResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                val email = data?.getStringExtra("email")
                val passWord = data?.getStringExtra("passWord")
                with(binding){
                    emailText.setText(email)
                    pwText.setText(passWord)
                }
            }
        }
        with(binding){
            signUpText.setOnClickListener{
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                signUpResult.launch(intent)
            }
        }
    }
}