package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMovieBinding

class MovieViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {
        itemBinding.titleTv.text = movie.title
    }

}