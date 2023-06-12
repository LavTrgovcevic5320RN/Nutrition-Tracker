package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMovieBinding

class MealViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: Meal) {
        itemBinding.titleTv.text = meal.name
        Picasso
            .get()
            .load(meal.image)
            .into(itemBinding.mealImage);


        itemBinding.moreInfo.setOnClickListener {
            showDialog(itemBinding.root.context, meal)
        }
    }

    private fun showDialog(context: Context, meal: Meal) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(meal.name)
            .setMessage(meal.name)
            .setPositiveButton("U redu") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

}