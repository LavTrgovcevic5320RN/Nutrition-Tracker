package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivityMainBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.adapters.MainPagerAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.viewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager,
                this
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    fun openMeals(category: Category) {
        binding.viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_2, false);
        val sharedPreferences = getSharedPreferences("category", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("category", category.name)
        editor.apply()
    }

    fun openMeal() {
        Timber.e("Otvara detaljan pregled za jelo")
//        supportFragmentManager.beginTransaction().add(R.id.meal_layout, MealsFragment()).commit()
    }


}
