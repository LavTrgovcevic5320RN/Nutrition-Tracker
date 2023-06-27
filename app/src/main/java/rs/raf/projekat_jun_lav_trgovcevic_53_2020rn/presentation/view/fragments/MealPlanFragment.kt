package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
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
    private lateinit var selectedMeal: SavedMeal
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
        binding.addButton.setOnClickListener{
//            val selectedDay: String = binding.daySpinner.selectedItem.toString()
            val selectedDay = binding.planTitle.text.toString()
            val selectedMealType: String = binding.mealSpinner.selectedItem.toString()
            val mealsForSelectedDay = mapOfSavedMeals[selectedDay]

            if(::selectedMeal.isInitialized ){
                if(selectedMeal.type == selectedMealType) {
                    if (mealsForSelectedDay == null) {
                        mapOfSavedMeals[selectedDay] = mutableListOf(selectedMeal)
                    } else {
                        if (!mealsForSelectedDay.contains(selectedMeal) and !mealsForSelectedDay.any { it.type == selectedMealType }) {
                            mapOfSavedMeals[selectedDay]!!.add(selectedMeal)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Jelo već postoji u mapi ili obrok je već izabran za ovaj dan.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    Timber.e("----------------------------------------")
                    for ((day, meals) in mapOfSavedMeals) {
                        Timber.e(day)
                        for (meal in meals) {
                            Timber.e(meal.name)
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "Izabrano jelo nema isti tip obroka kao izabrani obrok", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Izaberite neko jelo", Toast.LENGTH_SHORT).show()
            }
        }

        binding.sendButton.setOnClickListener {
            val daysOfWeek = listOf("Ponedeljak", "Utorak", "Sreda", "Četvrtak", "Petak", "Subota", "Nedelja")
            val currentIndex = daysOfWeek.indexOf(binding.planTitle.text)
            val nextIndex = currentIndex + 1

            if (currentIndex < daysOfWeek.size - 1) {
                val currentDay = daysOfWeek[currentIndex]

                if (mapOfSavedMeals.containsKey(currentDay)) {
                    val nextDay = daysOfWeek[nextIndex]
                    binding.planTitle.text = nextDay

                    if (daysOfWeek[currentIndex] == "Nedelja") {
                        Toast.makeText(requireContext(), "Jela su uspešno dodata za sve dane u nedelji. Možete poslati plan.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Morate dodati jelo za $currentDay pre nego što pređete na sledeći dan.", Toast.LENGTH_SHORT).show()
                }
            } else {
//                Timber.e("Usao")
                binding.sendButton.text = "Pošalji jelo"
                binding.email.visibility = View.VISIBLE

                val email = binding.email.text.toString()
                if(binding.email.visibility == View.VISIBLE){
                    if (isValidEmail(email)) {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_SUBJECT, "Plan obroka")

                            // Sastavljanje tela mejla
                            val emailBody = buildEmailBody()
                            putExtra(Intent.EXTRA_TEXT, emailBody)

                            // Dodavanje linka koji otvara aplikaciju
                            val appLink = "myapp://open"
                            putExtra(Intent.EXTRA_HTML_TEXT, "$emailBody<br><a href=\"$appLink\">Otvori aplikaciju</a>")
                        }

                        if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                            startActivity(emailIntent)
                        } else {
                            Toast.makeText(requireContext(), "Nije pronađena aplikacija za slanje mejla.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Nije validna email adresa.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun buildEmailBody(): String {
        val stringBuilder = StringBuilder()

        for ((day, meals) in mapOfSavedMeals) {
            stringBuilder.append("$day:\n")

            for (meal in meals) {
                stringBuilder.append("Obrok: ${meal.type}\n")
                stringBuilder.append("Jelo: ${meal.name}\n")
                stringBuilder.append("----------\n")
            }

            stringBuilder.append("\n")
        }

        return stringBuilder.toString()
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

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun setClickedMeal(clickedMeal: SavedMeal){
        selectedMeal = clickedMeal
    }

    override fun onResume() {
        super.onResume()
        init()
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