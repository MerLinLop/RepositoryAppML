package com.example.appml._view.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appml.R
import kotlinx.android.synthetic.main.item_destacado.view.*

class DestacadoViewPagerAdapter(
    val list: MutableList<String> = mutableListOf()
) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): View {

        var v = LayoutInflater.from(container.context).inflate(
            R.layout.item_destacado,
            container,
            false
        ) as ViewGroup

        var item = list[position]

        v.imageViewDestacado.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(v.imageViewDestacado.context)
            .load(Uri.parse(item))
            .apply(
                RequestOptions()
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .fitCenter()
            )
            .into(v.imageViewDestacado)
        container.addView(v)
        return v
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


    override fun getCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }
    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        container.removeView(item as View)
    }
    fun add(listAux: List<String>) {

        for (item in listAux) {
            list.add(item)
            notifyDataSetChanged()
        }
    }
}