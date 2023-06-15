package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMealBinding
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.MealFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.SearchMealsFragment
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.diff.MealDiffCallBack
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder.MealViewHolder
import timber.log.Timber

class MealAdapter(private val fragment: SearchMealsFragment) : ListAdapter<Meal, MealViewHolder>(MealDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
        val meal: Meal = getItem(position)

        holder.itemView.setOnClickListener {
//            val fragmentManager = fragment.fragmentManager!! // or childFragmentManager if you are in a Fragment
//            val bundle = bundleOf("poruka" to "Cao Lave")
//
//            fragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainerMealInfo, MealFragment().apply {
//                    arguments = Bundle().apply {
//                        putString("NAME", meal.name)
//                    }
//                })
//                addToBackStack(null)
//                commit()
//            }

//            val nextFrag = MealFragment()
//            getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R.id.fragmentContainerMealInfo, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()
            Timber.d(meal.name)
            fragment.setSelectedMeal(meal)
            val newFragment = MealFragment()
            fragment.requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerMealInfo, newFragment)
//                .detach(fragment)
                .hide(fragment)
                .addToBackStack(null)
                .commit()
        }

    }

}