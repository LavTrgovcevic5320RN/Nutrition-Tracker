package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.*
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.CategoriesState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.UsersState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (binding.passwordEditText.length() >= 4) {
                startMainActivity()
            } else {
                Toast.makeText(this, "Password must be at least 4 characters long", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startMainActivity() {
        saveLoginStatus()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveLoginStatus() {
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}