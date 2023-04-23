package com.radha.otpbasedlogintask

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.otpbasedlogintask.R
import org.json.JSONException

class LoginActivity : AppCompatActivity() {
    lateinit var mobileNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editText = findViewById<EditText>(R.id.phonemojiTextInputEditText)
        val button = findViewById<Button>(R.id.loginBtn)


        button.setOnClickListener {
            try {
                mobileNumber = editText.getText().toString()

                if (mobileNumber.startsWith("+") || mobileNumber.length > 10) {

//                    var newDigit=mobileNumber.substring(0,3)
                    val firsDigit = mobileNumber.substring(4, 5)
                    val secondDigit = mobileNumber.substring(5, 6)
                    val nineDigit = mobileNumber.substring(13, 14)
                    val tenDigit = mobileNumber.substring(14, 15)

                    showCibilDialog(firsDigit, secondDigit, nineDigit, tenDigit)
                } else {
                    Toast.makeText(
                        this, "Please enter valid Number", Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this, "Please enter valid mobile number", Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    private fun showCibilDialog(
        firsDigit: String, secondDigit: String, nineDigit: String, tenDigit: String
    ) {
        val dialogView: View = layoutInflater.inflate(R.layout.popup, null)
        try {
            (dialogView.findViewById<View>(R.id.otp1) as TextView).text = firsDigit
            (dialogView.findViewById<View>(R.id.otp2) as TextView).text = secondDigit
            (dialogView.findViewById<View>(R.id.otp3) as TextView).text = nineDigit
            (dialogView.findViewById<View>(R.id.otp4) as TextView).text = tenDigit

            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            builder.setCancelable(false)
            val alertDialog = builder.create()

            dialogView.findViewById<View>(R.id.popupLayout).setOnClickListener {
                alertDialog.dismiss()
                val intent = Intent(this, VarificationActivity::class.java)
                intent.putExtra("mobile", mobileNumber)
                intent.putExtra("firsDigit", firsDigit)
                intent.putExtra("secondDigit", secondDigit)
                intent.putExtra("nineDigit", nineDigit)
                intent.putExtra("tenDigit", tenDigit)
                startActivity(intent)
            }
            alertDialog.show()
        } catch (e: JSONException) {
            e.printStackTrace()
            Toast.makeText(
                this, "Some Error Occurred ", Toast.LENGTH_SHORT
            ).show()
        }
    }
}