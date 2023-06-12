package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.LoginFragment
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 3000
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView = findViewById(R.id.splash_image)

        val isLoggedIn = checkIfUserLoggedIn()

        if (isLoggedIn) {
            Timber.d("Ulogovan je")
            startMainActivity()
        } else {
            Timber.d("Nije ulogovan")

            startLoginActivity()
        }
    }

    fun checkIfUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun startMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }

    fun startLoginActivity() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }

//    fun startLoginActivity() {
//        Handler().postDelayed({
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, LoginFragment())
//                .addToBackStack(null)
//                .commit()
//
//            imageView.visibility = View.INVISIBLE
//        }, splashDuration)
//    }
}
