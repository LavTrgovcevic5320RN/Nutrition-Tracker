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

    override fun onResume() {
        Timber.e("Categorija 1:" + mainViewModel.selectedCategory.name)
        super.onResume()
    }

    override fun onAttach(context: Context) {
        Timber.e("Categorija 2:" + mainViewModel.selectedCategory.name)
        super.onAttach(context)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Timber.e("Categorija 3:" + mainViewModel.selectedCategory.name)
        super.onConfigurationChanged(newConfig)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Timber.e("Categorija 4:" + mainViewModel.selectedCategory.name)
        return super.onContextItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.e("Categorija 5:" + mainViewModel.selectedCategory.name)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        Timber.e("Categorija 6:" + mainViewModel.selectedCategory.name)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        Timber.e("Categorija 7:" + mainViewModel.selectedCategory.name)
        return super.onCreateAnimator(transit, enter, nextAnim)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        Timber.e("Categorija 8:" + mainViewModel.selectedCategory.name)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.e("Categorija 9:" + mainViewModel.selectedCategory.name)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        Timber.e("Categorija 10:" + mainViewModel.selectedCategory.name)
        super.onDestroy()
    }

    override fun onDestroyOptionsMenu() {
        Timber.e("Categorija 11:" + mainViewModel.selectedCategory.name)
        super.onDestroyOptionsMenu()
    }

    override fun onDetach() {
        Timber.e("Categorija 12:" + mainViewModel.selectedCategory.name)
        super.onDetach()
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        Timber.e("Categorija 13:" + mainViewModel.selectedCategory.name)
        return super.onGetLayoutInflater(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Timber.e("Categorija 14:" + mainViewModel.selectedCategory.name)
        super.onHiddenChanged(hidden)
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        Timber.e("Categorija 15:" + mainViewModel.selectedCategory.name)
        super.onInflate(context, attrs, savedInstanceState)
    }

    override fun onLowMemory() {
        Timber.e("Categorija 16:" + mainViewModel.selectedCategory.name)
        super.onLowMemory()
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        Timber.e("Categorija 17:" + mainViewModel.selectedCategory.name)
        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.e("Categorija 18:" + mainViewModel.selectedCategory.name)
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        Timber.e("Categorija 19:" + mainViewModel.selectedCategory.name)
        super.onOptionsMenuClosed(menu)
    }

    override fun onPause() {
        Timber.e("Categorija 20:" + mainViewModel.selectedCategory.name)
        super.onPause()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        Timber.e("Categorija 21:" + mainViewModel.selectedCategory.name)
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Timber.e("Categorija 22:" + mainViewModel.selectedCategory.name)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        Timber.e("Categorija 23:" + mainViewModel.selectedCategory.name)
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.e("Categorija 24:" + mainViewModel.selectedCategory.name)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Timber.e("Categorija 26:" + mainViewModel.selectedCategory.name)
        super.onStop()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Timber.e("Categorija 27:" + mainViewModel.selectedCategory.name)
        super.onViewStateRestored(savedInstanceState)
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