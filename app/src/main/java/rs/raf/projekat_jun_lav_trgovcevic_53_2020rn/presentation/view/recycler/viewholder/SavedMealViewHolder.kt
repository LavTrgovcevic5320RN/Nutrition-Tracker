package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding

class SavedMealViewHolder(private val itemBinding: LayoutItemMealBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(savedMeal: SavedMeal) {
        itemBinding.mealNameTv.text = savedMeal.name
        Picasso
            .get()
            .load(savedMeal.image)
            .into(itemBinding.mealImage);
    }
}