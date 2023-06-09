package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMovieBinding

class CategoryViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: Category) {
        itemBinding.titleTv.text = category.name
    }

}