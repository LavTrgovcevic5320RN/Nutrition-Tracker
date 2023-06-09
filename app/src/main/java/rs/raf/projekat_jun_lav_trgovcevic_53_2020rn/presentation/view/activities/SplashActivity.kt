package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isLoggedIn = checkIfUserLoggedIn()

        if (isLoggedIn) {
            Timber.d("Ulogovan je")
            startMainActivity()
        } else {
            Timber.d("Nije ulogovan")

            startLoginActivity()
        }

//        Handler().postDelayed({
//            val intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, splashDuration)
    }


//    private fun checkIfUserLoggedIn(): Boolean {
//        // Implementirajte logiku za proveru da li se korisnik ranije prijavio
//        // Možete koristiti SharedPreferences, bazu podataka ili bilo koju drugu metodu za čuvanje podataka o prijavi korisnika
//        // Ovde se jednostavno simulira povratna vrednost kao da je korisnik već prijavljen
//        return true
//    }

    private fun checkIfUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun startMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)

//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
    }

    private fun startLoginActivity() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)

//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}
