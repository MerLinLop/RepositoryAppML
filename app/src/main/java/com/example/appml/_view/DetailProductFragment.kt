package com.example.appml._view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appml.R
import com.example.appml._model.remote.Results
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import kotlinx.android.synthetic.main.item_destacado.view.*
import java.io.Serializable


class DetailProductFragment: BaseFragment(), BasicMethods {


    lateinit var detailProductsFragmentView: View
    private lateinit var mContext: Context

    @BindView(R.id.textViewState)
    lateinit var textViewState: TextView
    @BindView(R.id.textViewCantVendidos)
    lateinit var textViewCantVendidos: TextView
    @BindView(R.id.textViewTitle)
    lateinit var textViewTitle: TextView
    @BindView(R.id.textViewPrice)
    lateinit var textViewPrice: TextView
    @BindView(R.id.textViewAvailableQuantity)
    lateinit var textViewAvailableQuantity: TextView
    @BindView(R.id.imageViewProductDetail)
    lateinit var imageViewProductDetail: ImageView

    lateinit var detailProduct: Results
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailProductsFragmentView = inflater.inflate(R.layout.fragment_detail_producto, container, false)
        ButterKnife.bind(this, detailProductsFragmentView)

        initObservables()
        init()
        initListeners()

        return detailProductsFragmentView
    }

    override fun initObservables() {
      //  (activity as MainActivity2?)!!.configurarBarra(false)
    }

    override fun init() {
        val bundle = arguments
        if (bundle != null) {
            detailProduct= bundle.getSerializable("product") as Results
            setData()

        }
    }

    override fun initListeners() {

    }
     fun setData(){
         if(detailProduct.condition.equals("new")){
             textViewState.text="Nuevo | "
         }
         textViewCantVendidos.text=" ${detailProduct.sold_quantity.toString()} vendidos"
         textViewTitle.text="${detailProduct.title}"
         textViewPrice.text="${detailProduct.price}"
         textViewAvailableQuantity.text="${detailProduct.available_quantity.toString()}"


         Glide.with(mContext)
             .load(Uri.parse(detailProduct.thumbnail))
             .apply(
                 RequestOptions()
                     .skipMemoryCache(false)
                     .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                     .fitCenter()
             )
             .into(imageViewProductDetail)
     }
}