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
        for(i in mainViewModel.selectedMeal.ingredients.indices){
            val ingredient = mainViewModel.selectedMeal.ingredients[i]
            val measure = mainViewModel.selectedMeal.measures[i]

            if (ingredient.isNotBlank())
                builder.append(ingredient).append(" : ").append(measure).append("\n")
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