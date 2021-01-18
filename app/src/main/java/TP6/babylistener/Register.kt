package TP6.babylistener

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        registerBtn.setOnClickListener{
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (Email.text.toString().isEmpty()) {
            Email.error = "Please enter email"
            Email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Email.error = "Please enter a valid email."
            Email.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password."
            password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(Email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }

    fun click_login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}