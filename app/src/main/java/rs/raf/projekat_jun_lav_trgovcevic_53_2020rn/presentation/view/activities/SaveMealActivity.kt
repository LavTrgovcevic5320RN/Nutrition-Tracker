package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SaveMealActivity : AppCompatActivity(R.layout.activity_save_meal) {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var mealImageView: ImageView
    private lateinit var mealNameTextView: TextView
    private lateinit var selectedDateTextView: TextView
    private lateinit var saveButton: Button

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_meal)
        init()
    }

    private fun init() {
        val bundle :Bundle ?=intent.extras
//        bundle?.apply {
//            //Parcelable Data
//            val meal: Meal? = getParcelable("selectedMeal")
//            if (meal != null) {
//                mainViewModel.selectedMeal = meal
//            }
//        }
        initUi()
        initListeners()
    }

    private fun initUi() {
        mealImageView = findViewById(R.id.mealImageView)
        mealNameTextView = findViewById(R.id.mealNameTextView)
        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        saveButton = findViewById(R.id.saveButton)

        Picasso
            .get()
            .load(mainViewModel.selectedMeal.image)
            .into(mealImageView);

        mealNameTextView.text = mainViewModel.selectedMeal.name

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd. MM. yyyy")
        val current = formatter.format(time)
        selectedDateTextView.text = current


    }

    private fun initListeners(){
        mealImageView.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

        selectedDateTextView.setOnClickListener {
            showDatePicker()
        }

        saveButton.setOnClickListener {
            saveMeal()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            mealImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            // Update selected date in the TextView
            val formattedDate = String.format("%02d. %02d. %d", selectedDay, selectedMonth + 1, selectedYear)
            selectedDateTextView.text = formattedDate
        }, year, month, day)

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // Set minimum date to today
        datePickerDialog.show()
    }

    private fun saveMeal() {
        // Logika za čuvanje jela

        // Prikazivanje obaveštenja o uspešnom čuvanju jela
        Toast.makeText(this, "Jelo uspešno sačuvano u meni.", Toast.LENGTH_SHORT).show()
    }


}