package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MainActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MealActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.ListMealsFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MealFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MealsFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.SearchMealsFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff.MealDiffCallBack
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder.MealViewHolder
import timber.log.Timber

class MealAdapter(private val fragment: ListMealsFragment) : ListAdapter<Meal, MealViewHolder>(MealDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
        val meal: Meal = getItem(position)

        holder.itemView.setOnClickListener {
            Timber.d(meal.name)
            fragment.setSelectedMeal(meal)

            val intent = Intent(fragment.context, MealActivity::class.java)
            intent.putExtra("meal", meal)
            fragment.context?.startActivity(intent)
        }
    }

//    fun filter(query: String) {
//        currentList.filter { item ->
//            item.contains(query, ignoreCase = true)
//        }
//        notifyDataSetChanged()
//    }

}