package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemMovieBinding
import timber.log.Timber

class CategoryViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: Category) {
        itemBinding.titleTv.text = category.name
//        Timber.e(category.image)
        Picasso
            .get()
            .load(category.image)
            .into(itemBinding.categoryImage);


        itemBinding.moreInfo.setOnClickListener {
            showDialog(itemBinding.root.context, category)
        }
    }

    private fun showDialog(context: Context, category: Category) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(category.name)
            .setMessage(category.description)
            .setPositiveButton("U redu") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

}