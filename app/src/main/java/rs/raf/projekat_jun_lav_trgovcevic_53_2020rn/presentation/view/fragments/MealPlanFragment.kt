package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMealPlanBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
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
        initObservers()

        binding.listMealRv.layoutManager = LinearLayoutManager(context)
        savedMealAdapter = SavedMealAdapter()
        binding.listMealRv.adapter = savedMealAdapter

        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY

        // Set the calendar to the current date
        calendar.time = Date()

        // Set the time to midnight
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // Find the start of the week
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

        // Get the start of the week
        val startOfWeek = calendar.time

        // Print the start of the week
        Timber.e("DAN: " + startOfWeek)
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
//                val adapter: ArrayAdapter<SavedMeal> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, savedMeals!!.toTypedArray())
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.mealSpinner.adapter = adapter
                savedMealAdapter.submitList(state.savedMeals)
            }
            is SaveMealState.Error -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getStartOfPreviousWeek(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}