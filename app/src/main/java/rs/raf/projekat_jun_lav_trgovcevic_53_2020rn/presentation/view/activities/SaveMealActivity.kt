package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.squareup.picasso.Picasso
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.ActivitySaveMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.SaveMealState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.io.*
import java.lang.Exception
import java.util.*

class SaveMealActivity : AppCompatActivity(R.layout.activity_save_meal) {
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    lateinit var binding: ActivitySaveMealBinding
    private lateinit var alertDialog : AlertDialog.Builder
    private lateinit var meal: Meal
    private val REQUEST_IMAGE_CAPTURE = 1
    private var photoTaken: Boolean = false
    private var pathToRemember: String = ""
    private val getPicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val bundle = it.data?.extras
            val finalPhoto: Bitmap = bundle?.get("data") as Bitmap

            val path: String? = saveToInternalStorage(finalPhoto, meal.name)
            if (path != null) {
                loadImageFromStorage(path, meal.name)
            }
            photoTaken = true
            pathToRemember = path.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseExtra()
        init()
    }

    private fun parseExtra(){
        meal = intent.getSerializableExtra("meal") as Meal
    }

    private fun init() {
        initUi()
        initDialog()
        initListeners()
        initObservers()
    }

    private fun initUi() {
        Picasso
            .get()
            .load(meal.image)
            .into(binding.mealImageView);

        binding. mealNameTextView.text = meal.name
    }

    private fun initDialog(){
        alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Allow FoodRecipes to open Camera?")

        alertDialog.setPositiveButton(R.string.dialogYes) { dialog, which ->
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try{
                getPicture.launch(takePictureIntent)
            }
            catch (exception: ActivityNotFoundException){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.setNegativeButton(R.string.dialogNo) { dialog, which ->
        }
    }

    private fun initListeners() {
        binding.mealImageView.setOnClickListener {
            alertDialog.show()
        }

        binding.saveButton.setOnClickListener {
            saveMeal()
        }
    }

    private fun initObservers(){
        mainViewModel.saveMealState.observe(this, androidx.lifecycle.Observer {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun renderState(state: SaveMealState){
        when (state) {
            is SaveMealState.Success -> {
                println("SUCCESS IN SAVE RECIPE STATE")
            }
            is SaveMealState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SaveMealState.DataFetched -> {
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is SaveMealState.SuccessfullySaved -> {
                Toast.makeText(this, "Recipe is successfully added to menu", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveMeal() {
        val dateToSave = Date(binding.datePicker1.year - 1900, binding.datePicker1.month, binding.datePicker1.dayOfMonth)

        lateinit var mealType : String
        if(binding.ToggleButtonGroup.selectedButtons.isNotEmpty()) {
            val button: ThemedButton = binding.ToggleButtonGroup.selectedButtons[0]
            mealType = button.text
        }else {
            Toast.makeText(this, "Molimo selektujte za koji obrok zelite da sacuvate jelo", Toast.LENGTH_SHORT).show()
            return
        }

        var newMeal: SavedMeal
        if(photoTaken){
            newMeal = SavedMeal(meal.id, meal.name, pathToRemember, meal.instructions, meal.youtube, meal.ingredients, meal.measures, meal.category, dateToSave, mealType)
        }else{
            newMeal = SavedMeal(meal.id, meal.name, meal.image, meal.instructions, meal.youtube, meal.ingredients, meal.measures, meal.category, dateToSave, mealType )
        }

        Timber.e(newMeal.id)
        mainViewModel.addSavedMeal(newMeal)
        Toast.makeText(this, "Jelo uspešno sačuvano u meni.", Toast.LENGTH_SHORT).show()
//        finish()
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap, fileName: String): String? {
        val cw = ContextWrapper(applicationContext)
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val myPath = File(directory, "$fileName.jpg")
        var fos: FileOutputStream? = null

        try{
            fos = FileOutputStream(myPath)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch(e: Exception){
            e.printStackTrace()
        } finally {
            try{
                fos?.close()
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }

    private fun loadImageFromStorage(path: String, fileName: String) {
        try{
            val file = File(path, "$fileName.jpg")
            Picasso
                .get()
                .load(file)
                .into(binding.mealImageView);
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }
    }

}
