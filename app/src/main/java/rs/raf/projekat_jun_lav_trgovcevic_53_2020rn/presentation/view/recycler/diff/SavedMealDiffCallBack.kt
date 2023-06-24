package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal

class SavedMealDiffCallBack: DiffUtil.ItemCallback<SavedMeal>() {

    override fun areItemsTheSame(oldItem: SavedMeal, newItem: SavedMeal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SavedMeal, newItem: SavedMeal): Boolean {
        return oldItem.name == newItem.name
    }

}