package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.animation.Animator
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentSearchMealsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MainActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter.MealAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.MealsState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListMealsFragment : Fragment(R.layout.fragment_list_meals) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentSearchMealsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
        var sharedPref : SharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)
        val categoryName = sharedPref.getString("category", "ne postoji")
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listMealRv.layoutManager = LinearLayoutManager(context)
        adapter = MealAdapter(this)
        binding.listMealRv.adapter = adapter
    }

    override fun onStart() {
        mainViewModel.getAllMealsFilterByCategory(mainViewModel.selectedCategory.name)
//        mainViewModel.getAllMeals()
//        mainViewModel.fetchAllMeals()
        Timber.e("Categorija 25:" + mainViewModel.selectedCategory.name)
        super.onStart()
    }


    private fun initListeners() {
        binding.timeButtonGroup1.setOnSelectListener {
            val vrsta = it.text
//            Log.d("Main", vrsta)

            binding.inputMealEt.doAfterTextChanged {
                val filter = it.toString()
                if(vrsta == "Name"){
                    mainViewModel.getAllMealsByName(filter)

                }else if(vrsta == "Ingredient"){
                    mainViewModel.getAllMealsByIngredient(filter)
                }
                Log.d("Main", it.toString())
            }
        }
    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun renderState(state: MealsState) {
        when (state) {
            is MealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals)
            }
            is MealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputMealEt.isVisible = !loading
        binding.listMealRv.isVisible = !loading
        binding.loadingMealPb.isVisible = loading
    }

    fun setSelectedMeal(meal: Meal){
        mainViewModel.selectedMeal = meal;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}