package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.FragmentStatisticsBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.SaveMealState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private var savedMeals: List<SavedMeal>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        initUi()
//        initListeners()
        initObservers()
    }
//    private fun initUi() {
//        val entries = loadStatisticsData()
//        if (entries.isNotEmpty()) {
//            setupChart(binding.mealChart, entries)
//            binding.mealChart.visibility = View.VISIBLE
//            binding.noDataTextView.visibility = View.GONE
//        } else {
//            binding.mealChart.visibility = View.GONE
//            binding.noDataTextView.visibility = View.VISIBLE
//        }
//    }
    private fun initObservers() {
        mainViewModel.saveMealState.observe(viewLifecycleOwner){
            renderSavedMealState(it)
        }
        mainViewModel.getAllSavedMeals()
//        Timber.e("Vreme: " + Calendar.getInstance().time.toString())
    }

    private fun renderSavedMealState(state: SaveMealState?) {
        when(state){
            is SaveMealState.Success -> {
                val aWeekAgo = getStartOfPreviousWeek().time
                val today = Calendar.getInstance().time
                savedMeals = state.savedMeals.filter{
                    it.date >= aWeekAgo && it.date <= today
                }.sortedBy { it.date }

                Timber.e("Velicina liste: " + savedMeals!!.size)
                for(i in savedMeals!!)
                    Timber.e("Filtrirano jelo: " + i.name)

                generateChart()
            }
            is SaveMealState.Error -> {
                Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateChart() {
        val dataMap = savedMeals!!.groupBy { it.date }.mapValues { it.value.size }
        println("Objekti")
        println(dataMap)

        val entries = dataMap.entries.mapIndexed { index, entry ->
            BarEntry(index.toFloat(), entry.value.toFloat())
        }

        val valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        }
        val dataSet = BarDataSet(entries, "Broj napravljenih jela")
        dataSet.valueFormatter = valueFormatter
        dataSet.valueTextSize = 16f

        val format = SimpleDateFormat("EEE dd.", Locale.getDefault())
        val labels = dataMap.keys.map { format.format(it) }.toTypedArray()

        Timber.e(labels.size.toString())
        binding.mealChart.xAxis.setCenterAxisLabels(true)
        binding.mealChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.mealChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.mealChart.xAxis.setDrawGridLines(false)
        binding.mealChart.xAxis.textSize = 20f
        binding.mealChart.xAxis.labelRotationAngle = -45f
        binding.mealChart.xAxis.setLabelCount(entries.size, false);
        binding.mealChart.axisLeft.isEnabled = false
        binding.mealChart.axisRight.isEnabled = false
        binding.mealChart.description.isEnabled = false
        binding.mealChart.legend.isEnabled = false

        binding.mealChart.setDrawBarShadow(false);
        binding.mealChart.setDrawValueAboveBar(true);

        val barData = BarData(dataSet)
        binding.mealChart.data = barData
        binding.mealChart.setFitBars(true)
        binding.mealChart.invalidate()


//        val labelCount = labels.size
//        val chartWidth = labelCount * 100f // Adjust the value as needed
//        binding.mealChart.layoutParams.width = chartWidth.toInt()
    }

    private fun getStartOfPreviousWeek(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class IntegerAxisValueFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return value.toInt().toString()
    }
}