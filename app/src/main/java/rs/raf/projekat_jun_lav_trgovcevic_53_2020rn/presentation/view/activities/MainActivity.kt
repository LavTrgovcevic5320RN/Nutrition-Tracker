package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivityMainBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.adapters.MainPagerAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MainFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MoreInfoFragment
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
//        initUi()
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutMain, MainFragment()).commit()
    }

}
