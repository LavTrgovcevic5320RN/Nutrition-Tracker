package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMealCategoryBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.CategoriesState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class MealCategoryFragment : Fragment(R.layout.fragment_meal_category)  {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMealCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter(this)
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
        binding.inputEt.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getCategoriesByName(filter)
        }
    }

    private fun initObservers() {
        mainViewModel.categoriesState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        mainViewModel.getAllCategories()
        mainViewModel.fetchAllCategories()
    }

    private fun renderState(state: CategoriesState) {
        when (state) {
            is CategoriesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.categories)
            }
            is CategoriesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoriesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is CategoriesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputEt.isVisible = !loading
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    fun setSelectedCategory(category: Category){
        mainViewModel.selectedCategory = category;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}