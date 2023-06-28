package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.*
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.UsersState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private var users: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()


    }

    private fun init(){
        initListeners()
        initObservers()
    }

    private fun initListeners(){
        binding.loginButton.setOnClickListener {
            if (binding.passwordEditText.length() >= 4) {
                var exists = false
                for(i in users!!){
                    Timber.e("Korisnik:  " + i.name)
                    if((i.name == binding.usernameEditText.text.toString()) and (i.password == binding.passwordEditText.text.toString())){
                        exists = true
                    }
                }
                if(exists){
                    startMainActivity()
                }else{
                    Toast.makeText(this, "Your username or password are incorrect or the user doesnt exist", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Password must be at least 4 characters long", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers(){
        mainViewModel.usersState.observe(this){
            renderUsersState(it)
        }
        mainViewModel.getAllUsers()
    }

    private fun renderUsersState(state: UsersState?) {
        when(state){
            is UsersState.Success -> {
                users = state.users
            }
            is UsersState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
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