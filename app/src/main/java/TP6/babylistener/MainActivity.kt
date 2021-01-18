package TP6.babylistener

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

    }



    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }else {
            Toast.makeText(
                baseContext, "Login Failed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun doLogin(view: View){
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

        auth.signInWithEmailAndPassword(Email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    // ...
                }

                // ...
            }
    }

    fun click_register(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }



}