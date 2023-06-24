package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.R
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.fragments.*

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2

    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> MealCategoryFragment()
            FRAGMENT_2 -> ListMealsFragment()
            else -> InfoFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> context.getString(R.string.categories)
            FRAGMENT_2 -> context.getString(R.string.meals)
            else -> context.getString(R.string.info)
        }
    }

}