package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

class FilterMealsActivity : AppCompatActivity() {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var imageView: ImageView
    private lateinit var saveMealButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_meals)
        init()

    }

    private fun init() {
//        val bundle :Bundle ?=intent.extras
//        bundle?.apply {
//            //Parcelable Data
//            val meal: Meal? = getSerializable("meal")
//            if (meal != null) {
//                mainViewModel.selectedMeal = meal
//            }
//        }
        initUi()
        initListeners()
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun initUi() {
        TODO("Not yet implemented")
    }
}