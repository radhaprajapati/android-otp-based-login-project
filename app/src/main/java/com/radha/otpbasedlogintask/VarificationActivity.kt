package com.radha.otpbasedlogintask

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.otpbasedlogintask.R

class VarificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_varification)

        val button = findViewById<Button>(R.id.submitBtn)
        val mobileNumber = findViewById<TextView>(R.id.mobileNumber)
        val otpA = findViewById<EditText>(R.id.otpA)
        val otpB = findViewById<EditText>(R.id.otpB)
        val otpC = findViewById<EditText>(R.id.otpC)
        val otpD = findViewById<EditText>(R.id.otpD)

        mobileNumber.text = intent.extras?.getString("mobile")

        button.setOnClickListener {

            val firsDigit = intent.extras?.getString("firsDigit").toString().trim()
            val secondDigit = intent.extras?.getString("secondDigit").toString().trim()
            val nineDigit = intent.extras?.getString("nineDigit").toString().trim()
            val tenDigit = intent.extras?.getString("tenDigit").toString().trim()

            val a=otpA.text.toString().trim();
            val b=otpB.text.toString().trim();
            val c=otpC.text.toString().trim();
            val d=otpD.text.toString().trim();

            if (firsDigit==a && secondDigit==b && nineDigit==c && tenDigit==d ){
                val intent = Intent(this, ProfileDetail::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Please validate OTP",Toast.LENGTH_SHORT).show()
            }

        }
    }
}