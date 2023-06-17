package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

class MealActivity : AppCompatActivity(R.layout.activity_meal) {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var imageView: ImageView
    private lateinit var saveMealButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        init()
    }

    private fun init() {
        val bundle :Bundle ?=intent.extras
        bundle?.apply {
            //Parcelable Data
            val meal: Meal? = getParcelable("selectedMeal")
            if (meal != null) {
                mainViewModel.selectedMeal = meal
            }
        }
        initUi()
        initListeners()
    }

    private fun initUi() {
        imageView = findViewById(R.id.ivMealImage)
        saveMealButton = findViewById(R.id.btnSaveMeal)
        Picasso
            .get()
            .load(mainViewModel.selectedMeal.image)
            .into(imageView);

        findViewById<TextView>(R.id.tvMealName).text = mainViewModel.selectedMeal.name
        findViewById<TextView>(R.id.tvCategory).text = "Category:   " + mainViewModel.selectedMeal.category
        findViewById<TextView>(R.id.tvArea).text = "Area:   " +  mainViewModel.selectedMeal.area
        findViewById<TextView>(R.id.tvInstructions).text =  "Instructions:\n\n" +  mainViewModel.selectedMeal.instructions

        if(mainViewModel.selectedMeal.tags != "null" && mainViewModel.selectedMeal.tags.isNotEmpty())
            findViewById<TextView>(R.id.tvTags).text = "Tags:   " + mainViewModel.selectedMeal.tags
        else
            findViewById<TextView>(R.id.tvTags).text = "Tags Not available"

        if(mainViewModel.selectedMeal.youtube != "null" && mainViewModel.selectedMeal.youtube.isNotEmpty())
            findViewById<TextView>(R.id.tvVideoLink).text = "Youtube link:   " + mainViewModel.selectedMeal.youtube
        else
            findViewById<TextView>(R.id.tvVideoLink).text = "Video link Not available"

        val builder = StringBuilder()
        builder.append("Ingredients and measures:\n\n")
        if (mainViewModel.selectedMeal.ingredient1 != "null" && mainViewModel.selectedMeal.ingredient1.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient1).append(" : ").append(mainViewModel.selectedMeal.measure1).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient2 != "null" && mainViewModel.selectedMeal.ingredient2.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient2).append(" : ").append(mainViewModel.selectedMeal.measure2).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient3 != "null" && mainViewModel.selectedMeal.ingredient3.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient3).append(" : ").append(mainViewModel.selectedMeal.measure3).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient4 != "null" && mainViewModel.selectedMeal.ingredient4.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient4).append(" : ").append(mainViewModel.selectedMeal.measure4).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient5 != "null" && mainViewModel.selectedMeal.ingredient5.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient5).append(" : ").append(mainViewModel.selectedMeal.measure5).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient6 != "null" && mainViewModel.selectedMeal.ingredient6.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient6).append(" : ").append(mainViewModel.selectedMeal.measure6).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient7 != "null" && mainViewModel.selectedMeal.ingredient7.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient7).append(" : ").append(mainViewModel.selectedMeal.measure7).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient8 != "null" && mainViewModel.selectedMeal.ingredient8.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient8).append(" : ").append(mainViewModel.selectedMeal.measure8).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient9 != "null" && mainViewModel.selectedMeal.ingredient9.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient9).append(" : ").append(mainViewModel.selectedMeal.measure9).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient10 != "null" && mainViewModel.selectedMeal.ingredient10.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient10).append(" : ").append(mainViewModel.selectedMeal.measure10).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient11 != "null" && mainViewModel.selectedMeal.ingredient11.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient11).append(" : ").append(mainViewModel.selectedMeal.measure11).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient12 != "null" && mainViewModel.selectedMeal.ingredient12.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient12).append(" : ").append(mainViewModel.selectedMeal.measure12).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient13 != "null" && mainViewModel.selectedMeal.ingredient13.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient13).append(" : ").append(mainViewModel.selectedMeal.measure13).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient14 != "null" && mainViewModel.selectedMeal.ingredient14.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient14).append(" : ").append(mainViewModel.selectedMeal.measure14).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient15 != "null" && mainViewModel.selectedMeal.ingredient15.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient15).append(" : ").append(mainViewModel.selectedMeal.measure15).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient16 != "null" && mainViewModel.selectedMeal.ingredient16.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient16).append(" : ").append(mainViewModel.selectedMeal.measure16).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient17 != "null" && mainViewModel.selectedMeal.ingredient17.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient17).append(" : ").append(mainViewModel.selectedMeal.measure17).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient18 != "null" && mainViewModel.selectedMeal.ingredient18.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient18).append(" : ").append(mainViewModel.selectedMeal.measure18).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient19 != "null" && mainViewModel.selectedMeal.ingredient19.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient19).append(" : ").append(mainViewModel.selectedMeal.measure19).append("\n")
        }
        if (mainViewModel.selectedMeal.ingredient20 != "null" && mainViewModel.selectedMeal.ingredient20.isNotEmpty()) {
            builder.append(mainViewModel.selectedMeal.ingredient20).append(" : ").append(mainViewModel.selectedMeal.measure20).append("\n")
        }

        findViewById<TextView>(R.id.tvIngredientsAndMeasures).text = builder.toString()
    }

    private fun initListeners() {
        saveMealButton.setOnClickListener{
            val intent = Intent(this, SaveMealActivity::class.java)
//            intent.putExtra("selectedMeal", meal)
            startActivity(intent)
            finish()
        }
    }

}