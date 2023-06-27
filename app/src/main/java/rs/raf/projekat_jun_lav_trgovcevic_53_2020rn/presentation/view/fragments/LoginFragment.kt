package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivityLoginBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentStatisticsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MainActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.SaveMealState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.UsersState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.util.*

class LoginFragment : Fragment(R.layout.activity_login){
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private var users: List<User>? = null
    private lateinit var titleName: String

//    private lateinit var usernameEditText: EditText
//    private lateinit var passwordEditText: EditText
//    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (password.length >= 4) {
                saveLoginStatus()
                startMainActivity()
            } else {
                Toast.makeText(context, "Password must be at least 4 characters long", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        mainViewModel.usersState.observe(this, Observer {
            renderUserState(it)
            Timber.e(it.toString())
        })

        mainViewModel.getAllUsers()
    }

    private fun renderUserState(state: UsersState?) {
        when(state){
            is UsersState.Success -> {
                users = state.users
            }
            is UsersState.Error -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startMainActivity() {
//        saveLoginStatus()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }

    private fun saveLoginStatus() {
        val sharedPreferences = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).supportActionBar!!.title = titleName
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titleName = (requireActivity() as MainActivity).supportActionBar!!.title.toString()
        (requireActivity() as MainActivity).supportActionBar!!.title = "Statistika"
    }

}