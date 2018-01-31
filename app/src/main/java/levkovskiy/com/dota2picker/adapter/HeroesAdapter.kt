package levkovskiy.com.dota2picker.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_heroes.view.*
import levkovskiy.com.dota2picker.R
import levkovskiy.com.dota2picker.Utils.BASE_URL
import levkovskiy.com.dota2picker.model.DataResponse


class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.ItemHolder>() {
    private val photos: ArrayList<DataResponse.Hero> = ArrayList()
    override fun getItemCount() = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_heroes, parent, false))

    override fun onBindViewHolder(holder: ItemHolder, position: Int) = holder.bind(photos[position], null)


    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DataResponse.Hero, listener: ((Any, Int) -> Unit)?) = with(itemView) {
            tv_hero_name.text = item.localizedName
            loadUrl(iv_icon, Uri.parse(BASE_URL + item.icon.subSequence(1, item.icon.length)))

        }

        private fun loadUrl(image: ImageView, url: Uri) {
            Picasso.with(image.context).load(url).into(image)
        }
    }

    fun clear() {
        photos.clear()
    }

    fun add(item: DataResponse.Hero) {
        photos.add(item)
        notifyDataSetChanged()
    }


}