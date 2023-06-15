package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding

class MealViewHolder(private val itemBinding: LayoutItemMealBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: Meal) {
        itemBinding.mealNameTv.text = meal.name
        Picasso
            .get()
            .load(meal.image)
            .into(itemBinding.mealImage);
    }
}