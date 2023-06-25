package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentListMealsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter.MealAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.MealsState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListMealsFragment : Fragment(R.layout.fragment_list_meals) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentListMealsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mealAdapter: MealAdapter
    private lateinit var savedMealAdapter: SavedMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler() {
        binding.listMealRv.layoutManager = LinearLayoutManager(context)
        mealAdapter = MealAdapter(this)
        binding.listMealRv.adapter = mealAdapter
    }

    private fun initListeners() {
        binding.timeButtonGroup1.setOnSelectListener {
            val vrsta = it.text

            binding.inputMealEt.doAfterTextChanged {
                val filter = it.toString()
                if(vrsta == "Name"){
//                    mealAdapter = MealAdapter(this)
//                    binding.listMealRv.adapter = mealAdapter
//                    mainViewModel.getAllMealsByName(filter)
//                    mealAdapter.currentList.filter { meal: Meal? ->
//                        meal!!.name.contains(filter, ignoreCase = true)
//                    }
//                    mealAdapter.notifyDataSetChanged()
                }else if(vrsta == "Ingredient"){
//                    mealAdapter = MealAdapter(this)
//                    binding.listMealRv.adapter = mealAdapter
//                    mainViewModel.filterKeyword = filter
                    mainViewModel.getAllMealsByIngredient(filter)
                }else if(vrsta == "My Meals"){
//                    val adapter = SavedMealAdapter()
//                    binding.listMealRv.adapter = adapter
//                    mainViewModel.getAllSavedMealsByName(filter)
                }
            }
        }
    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.selectedCateg.observe(viewLifecycleOwner, Observer{
            mainViewModel.getAllMealsFilterByCategory(it.name)
        })


        mainViewModel.saveMealState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
        })

        mainViewModel.getAllMeals()
        mainViewModel.fetchAllMeals()
    }

    private fun renderState(state: MealsState) {
        when (state) {
            is MealsState.Success -> {
                showLoadingState(false)
                mealAdapter.submitList(state.meals)
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