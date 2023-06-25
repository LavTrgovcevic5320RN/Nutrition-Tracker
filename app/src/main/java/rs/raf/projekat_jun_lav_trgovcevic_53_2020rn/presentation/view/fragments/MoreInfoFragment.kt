package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentMoreInfoBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class MoreInfoFragment : Fragment(R.layout.fragment_more_info) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMoreInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.savedMealsImgView.setOnClickListener{
            Timber.e("Usao u saved meals")
//            val intent = Intent(context, ProfileActivity::class.java)
//            startActivity(intent)
        }

        binding.mealPlanImgView.setOnClickListener{
            Timber.e("Usao u meal plan")
        }

        binding.StatisticsImgView.setOnClickListener{
            Timber.e("Usao u statistics")
            for(i in  parentFragment!!.parentFragmentManager.fragments){
                Timber.e(i.toString())
            }
            parentFragment!!.parentFragmentManager.beginTransaction().replace(R.id.frameLayoutMain, StatisticsFragment()).addToBackStack("statisticsFragment").commit()
//            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frameLayoutMain, StatisticsFragment()).addToBackStack("statisticsFragment").commit()    I ovaj nacin isto radi
        }

        binding.profileImgView.setOnClickListener{
            Timber.e("Usao u profile")
        }
    }
}