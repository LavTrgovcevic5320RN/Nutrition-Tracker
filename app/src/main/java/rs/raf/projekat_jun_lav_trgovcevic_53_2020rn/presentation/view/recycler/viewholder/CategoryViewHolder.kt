package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.databinding.LayoutItemCategoryBinding

class CategoryViewHolder(private val itemBinding: LayoutItemCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: Category) {
        itemBinding.titleTv.text = category.name
        Picasso
            .get()
            .load(category.image)
            .into(itemBinding.mealImage);


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