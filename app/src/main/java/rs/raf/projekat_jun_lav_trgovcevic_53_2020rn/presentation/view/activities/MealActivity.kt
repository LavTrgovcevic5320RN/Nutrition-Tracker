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
import timber.log.Timber

class MealActivity : AppCompatActivity(R.layout.activity_meal) {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var imageView: ImageView
    private lateinit var saveMealButton: Button
    private lateinit var shownMeal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        init()
    }

    private fun init() {
        parseExtra()
        initUi()
        initListeners()
    }

    private fun parseExtra(){
        shownMeal = intent.getSerializableExtra("meal") as Meal
    }

    private fun initUi() {
        imageView = findViewById(R.id.ivMealImage)
        saveMealButton = findViewById(R.id.btnSaveMeal)
        Picasso
            .get()
            .load(shownMeal.image)
            .into(imageView);

        findViewById<TextView>(R.id.tvMealName).text = shownMeal.name
        findViewById<TextView>(R.id.tvCategory).text = "Category:   " + shownMeal.category
        findViewById<TextView>(R.id.tvArea).text = "Area:   " +  shownMeal.area
        findViewById<TextView>(R.id.tvInstructions).text =  "Instructions:\n\n" +  shownMeal.instructions

        if(shownMeal.tags != "null" && shownMeal.tags.isNotEmpty())
            findViewById<TextView>(R.id.tvTags).text = "Tags:   " + shownMeal.tags
        else
            findViewById<TextView>(R.id.tvTags).text = "Tags Not available"

        if(shownMeal.youtube != "null" && shownMeal.youtube.isNotEmpty())
            findViewById<TextView>(R.id.tvVideoLink).text = "Youtube link:   " + shownMeal.youtube
        else
            findViewById<TextView>(R.id.tvVideoLink).text = "Video link Not available"

        val builder = StringBuilder()
        builder.append("Ingredients and measures:\n\n")
        for(i in shownMeal.ingredients.indices){
            val ingredient = shownMeal.ingredients[i]
            val measure = shownMeal.measures[i]

            if (ingredient != null && ingredient.isNotEmpty())
                builder.append(ingredient).append(" : ").append(measure).append("\n")
        }

        findViewById<TextView>(R.id.tvIngredientsAndMeasures).text = builder.toString()
    }

    private fun initListeners() {
        saveMealButton.setOnClickListener{
            val intent = Intent(this, SaveMealActivity::class.java)
            intent.putExtra("meal", shownMeal)
            startActivity(intent)
            finish()
        }
    }

}