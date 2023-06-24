package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivitySaveMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivityStatisticsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

class StatisticsActivity : AppCompatActivity() {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    lateinit var binding: ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){

    }
}