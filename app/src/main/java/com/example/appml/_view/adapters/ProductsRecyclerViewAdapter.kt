package com.example.appml._view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.appml.R
import com.example.appml._model.remote.Results
import kotlinx.android.synthetic.main.item_destacado.view.*

class ProductsRecyclerViewAdapter(
    var context: Context,
    var listProducts: List<Results>
) :
    RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsViewHolder>(){

    var mInflater: LayoutInflater
    lateinit var listener: ProductsRecyclerViewAdapterListener

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewProduct: ImageView
        var textViewNameProduct: TextView
        var textViewPrice: TextView
        init {
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct)
            textViewNameProduct = itemView.findViewById(R.id.textViewNameProduct)
            textViewPrice = itemView.findViewById(R.id.textViewPrice)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = mInflater.inflate(R.layout.item_producto, parent, false)

        //itemView.setOnClickListener(this);
        return ProductsViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val word = listProducts[position]
        holder.textViewNameProduct.text = "${word.title}"
        holder.textViewPrice.text = "$${word.price}"

        Glide.with(context)
            .load(Uri.parse(word.thumbnail))
            .apply(
                RequestOptions()
                    .transform( RoundedCorners(10))
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .fitCenter()
            )
            .transition(withCrossFade())
            .into(holder.imageViewProduct)

        holder.itemView.setOnClickListener {
            listener.onClick(word)
        }


    }
    override fun getItemCount(): Int {
        return listProducts.size
    }

    fun setListner(listener: ProductsRecyclerViewAdapterListener) {
        this.listener = listener
    }

    interface ProductsRecyclerViewAdapterListener {
        fun onClick(word: Results)

    }
}