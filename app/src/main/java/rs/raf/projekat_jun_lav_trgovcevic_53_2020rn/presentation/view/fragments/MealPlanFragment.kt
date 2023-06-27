package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMealPlanBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MainActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.SaveMealState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.util.*

class MealPlanFragment : Fragment(R.layout.fragment_meal_plan) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMealPlanBinding? = null
    private val binding get() = _binding!!
    private var savedMeals: List<SavedMeal>? = null
    private lateinit var savedMealAdapter: SavedMealAdapter
    private var mapOfSavedMeals: MutableMap<String, MutableList<SavedMeal>> = mutableMapOf()
    private lateinit var clickedSavedMeal: SavedMeal
    private lateinit var titleName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initListeners()
        initObservers()
    }

    private fun initView() {
        binding.listMealRv.layoutManager = LinearLayoutManager(context)
        savedMealAdapter = SavedMealAdapter(this)
        binding.listMealRv.adapter = savedMealAdapter

//        mapOfDays = mutableMapOf()
//        val daysOfWeek = listOf("Ponedeljak", "Utorak", "Sreda", "Četvrtak", "Petak", "Subota", "Nedelja")
//        for (day in daysOfWeek) {
//            mapOfDays[day] = mutableListOf()
//        }
    }

    private fun initListeners() {
        binding.daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedMealType = parent.getItemAtPosition(position).toString()
//                mainViewModel.getAllSavedMealsByType(selectedMealType)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        binding.planButton.setOnClickListener{
            val selectedDay: String = binding.daySpinner.selectedItem.toString()
            val selectedMealType: String = binding.mealSpinner.selectedItem.toString()
            val mealsForSelectedDay = mapOfSavedMeals[selectedDay]

            val isMealSaved = mealsForSelectedDay?.any {it.name == clickedSavedMeal.name} == true
            if (isMealSaved) {
                Toast.makeText(requireContext(), "The meal has already been added for: $selectedDay to the plan for: $selectedMealType ,please choose a different meal type or a different day", Toast.LENGTH_SHORT).show()
            } else {
                if(clickedSavedMeal.type != selectedMealType) {
                    Toast.makeText(requireContext(), "The selected meal isnt the same type as selected: $selectedMealType, please choose an suitable meal for this type", Toast.LENGTH_SHORT).show()
                }else{
                    Timber.e("Ulazio")
                    if (mealsForSelectedDay == null) {
                        mapOfSavedMeals[selectedDay] = mutableListOf(clickedSavedMeal)
                        Toast.makeText(requireContext(), "Added meal: ${clickedSavedMeal.name}", Toast.LENGTH_SHORT).show()
                    } else {
                        val hasSameTypeMeal = mealsForSelectedDay.any { it.type == selectedMealType }
                        if (hasSameTypeMeal) {
                            Toast.makeText(requireContext(), "You already chose a meal for this day: $selectedDay and meal type: $selectedMealType", Toast.LENGTH_SHORT).show()
                        } else {
                            mealsForSelectedDay.add(clickedSavedMeal)
                            Toast.makeText(requireContext(), "Added meal: ${clickedSavedMeal.name}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

//            Timber.e("----------------------------------------")
//            for((day, meals) in mapOfSavedMeals){
//                Timber.e(day)
//                for(meal in meals){
//                    Timber.e(meal.name)
//                }
//            }


        }
    }

    private fun initObservers() {
        mainViewModel.saveMealState.observe(viewLifecycleOwner){
            renderSavedMealState(it)
        }
        mainViewModel.getAllSavedMeals()
    }

    private fun renderSavedMealState(state: SaveMealState?) {
        when(state){
            is SaveMealState.Success -> {
                savedMeals = state.savedMeals
                savedMealAdapter.submitList(state.savedMeals)
            }
            is SaveMealState.Error -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    fun setClickedMeal(clickedMeal: SavedMeal){
        clickedSavedMeal = clickedMeal
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).supportActionBar!!.title = titleName
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titleName = (requireActivity() as MainActivity).supportActionBar!!.title.toString()
        (requireActivity() as MainActivity).supportActionBar!!.title = "Plan ishrane"
    }



}