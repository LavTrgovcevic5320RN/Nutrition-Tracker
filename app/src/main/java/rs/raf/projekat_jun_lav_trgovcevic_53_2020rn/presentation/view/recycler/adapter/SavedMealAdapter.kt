package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff.SavedMealDiffCallBack
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder.SavedMealViewHolder

class SavedMealAdapter : ListAdapter<SavedMeal, SavedMealViewHolder>(SavedMealDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SavedMealViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

}