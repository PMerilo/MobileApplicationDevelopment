package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegistationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.setOnClickListener {
            var notvalid = 0
            val inputArray = arrayOf(nameEdit, passEdit, emailEdit, adminEdit, pemEdit)
            for (input in inputArray) {
                input.error = if (input.text.isNullOrBlank()) "This field cannot be empty" else null
                notvalid += if (input.text.isNullOrBlank()) 1 else 0
            }
            if (notvalid == 0) {
                val toastmsg = """
                    Login name: ${nameEdit.text}
                    Password: ${passEdit.text}
                    Email: ${emailEdit.text}
                    Admin Number: ${adminEdit.text}
                    Pem Grp: ${pemEdit.text}
                """.trimIndent()
                displayToast(toastmsg)

                val verifyIntent = Intent(this, VerificationCodeActivity::class.java)
                startActivity(verifyIntent)
            }
        }
    }

    fun displayToast(message:String) {
        Toast.makeText( this, message, Toast.LENGTH_LONG).show()
    }
}