package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemCategoryBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.activities.MainActivity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.ListMealsFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MealCategoryFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff.CategoryDiffCallBack
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder.CategoryViewHolder
import timber.log.Timber


class CategoryAdapter(private val fragment: MealCategoryFragment) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding = LayoutItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        val category = getItem(position)

        holder.itemView.setOnClickListener {
            Timber.d(category.name)
            fragment.setSelectedCategory(category)


            (holder.itemView.context as MainActivity).openMeals(category)
        }



    }

}