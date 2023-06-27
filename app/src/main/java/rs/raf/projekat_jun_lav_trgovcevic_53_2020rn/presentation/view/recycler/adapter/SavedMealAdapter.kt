package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MealPlanFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff.SavedMealDiffCallBack
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder.SavedMealViewHolder

class SavedMealAdapter(private val fragment: Fragment) : ListAdapter<SavedMeal, SavedMealViewHolder>(SavedMealDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMealViewHolder(itemBinding)
    }

    private var selectedItemPosition = RecyclerView.NO_POSITION

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SavedMealViewHolder, position: Int) {
        holder.bind(getItem(position))

        val isSelected = position == selectedItemPosition
        if (isSelected) {
            holder.itemView.setBackgroundResource(R.drawable.selected_item_background)
        } else {
            holder.itemView.setBackgroundResource(0)
        }

        holder.itemView.setOnClickListener {
            setSelectedItemPosition(holder.adapterPosition)
            val savedMeal: SavedMeal = getItem(selectedItemPosition)
//            Toast.makeText(fragment.requireContext(),"Meal successfully selected: ${savedMeal.name}", Toast.LENGTH_SHORT).show()
            (fragment as MealPlanFragment).setClickedMeal(savedMeal)
        }
    }

}