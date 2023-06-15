package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

class MealFragment: Fragment() {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        Log.d("Main", mainViewModel.selectedMeal.name)

    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initObservers() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}