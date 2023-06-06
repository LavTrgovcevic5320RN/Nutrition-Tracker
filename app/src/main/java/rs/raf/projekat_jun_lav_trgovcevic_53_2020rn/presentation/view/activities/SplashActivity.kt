package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R

class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 3000 // Set the duration for your splash screen in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Replace with your actual layout file name

        // Delay navigation to the main activity
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }
}
