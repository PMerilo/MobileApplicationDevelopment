package nyp.sit.movieviewer.basic

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            var notvalid = 0
            val inputArray = arrayOf(nameEdit, passEdit)
            for (input in inputArray) {
                input.error = if (input.text.isNullOrBlank()) "This field cannot be empty" else null
                notvalid += if (input.text.isNullOrBlank()) 1 else 0
            }
            if (notvalid == 0) {
                if (nameEdit.text.toString() == "testuser" && nameEdit.text.toString() == "testuser") {
                    startActivity(Intent(this, SimpleViewListOfMoviesActivity::class.java))
                } else {
                    displayToast("Login Error")
                }
            }
        }
        registerBtn.setOnClickListener {
            startActivity(Intent(this, RegistationActivity::class.java))
        }
    }

    fun displayToast(message:String) {
        Toast.makeText( this, message, Toast.LENGTH_LONG).show()
    }
}