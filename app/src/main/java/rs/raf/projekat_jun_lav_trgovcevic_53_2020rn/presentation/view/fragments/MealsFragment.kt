package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMealsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

class MealsFragment : Fragment(R.layout.fragment_meals) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
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
        Picasso
            .get()
            .load(mainViewModel.selectedMeal.image)
            .into(binding.ivMealImage);

        binding.tvMealName.text = mainViewModel.selectedMeal.name
        binding.tvCategory.text = mainViewModel.selectedMeal.category
        binding.tvArea.text = mainViewModel.selectedMeal.area
        binding.tvInstructions.text = mainViewModel.selectedMeal.instructions
        binding.tvTags.text = mainViewModel.selectedMeal.tags
        binding.tvVideoLink.text = mainViewModel.selectedMeal.youtube

        val builder = StringBuilder()
        if (mainViewModel.selectedMeal.ingredient1.isNotBlank()) {
//            Timber.e("Ingredient1: " + mainViewModel.selectedMeal.ingredient1)
            builder.append(mainViewModel.selectedMeal.ingredient1).append(" : ").append(mainViewModel.selectedMeal.measure1).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient2.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient2).append(" : ").append(mainViewModel.selectedMeal.measure2).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient3.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient3).append(" : ").append(mainViewModel.selectedMeal.measure3).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient4.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient4).append(" : ").append(mainViewModel.selectedMeal.measure4).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient5.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient5).append(" : ").append(mainViewModel.selectedMeal.measure5).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient6.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient6).append(" : ").append(mainViewModel.selectedMeal.measure6).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient7.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient7).append(" : ").append(mainViewModel.selectedMeal.measure7).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient8.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient8).append(" : ").append(mainViewModel.selectedMeal.measure8).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient9.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient9).append(" : ").append(mainViewModel.selectedMeal.measure9).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient10.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient10).append(" : ").append(mainViewModel.selectedMeal.measure10).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient11.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient11).append(" : ").append(mainViewModel.selectedMeal.measure11).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient12.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient12).append(" : ").append(mainViewModel.selectedMeal.measure12).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient13.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient13).append(" : ").append(mainViewModel.selectedMeal.measure13).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient14.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient14).append(" : ").append(mainViewModel.selectedMeal.measure14).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient15.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient15).append(" : ").append(mainViewModel.selectedMeal.measure15).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient16.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient16).append(" : ").append(mainViewModel.selectedMeal.measure16).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient17.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient17).append(" : ").append(mainViewModel.selectedMeal.measure17).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient18.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient18).append(" : ").append(mainViewModel.selectedMeal.measure18).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient19.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient19).append(" : ").append(mainViewModel.selectedMeal.measure19).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient20.isNotBlank()) {
            builder.append(mainViewModel.selectedMeal.ingredient20).append(" : ").append(mainViewModel.selectedMeal.measure20).append("\n")
        }

        binding.tvIngredientsAndMeasures.text = builder.toString()

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