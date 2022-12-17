package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_verification_code.*

class VerificationCodeActivity : AppCompatActivity() {
    var validCode = 1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        verifyBtn.setOnClickListener {
            verifyBtn.error = if (verifyBtn.text.isNullOrBlank()) "Code cannot be empty" else null
            if (verifyBtn.text == validCode.toString()) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                displayToast("Code Error")
            }
        }
    }
    fun displayToast(message:String) {
        Toast.makeText( this, message, Toast.LENGTH_LONG).show()
    }
}