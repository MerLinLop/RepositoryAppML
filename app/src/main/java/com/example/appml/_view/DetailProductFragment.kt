package com.example.appml._view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appml.R
import com.example.appml._model.remote.Results
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import java.text.DecimalFormat


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
    @BindView(R.id.textViewDetailoShipping)
    lateinit var textViewDetailoShipping: TextView
    @BindView(R.id.textViewDetailoShippingFree)
    lateinit var textViewDetailoShippingFree: TextView
    @BindView(R.id.textViewDetailocityName)
    lateinit var textViewDetailocityName: TextView
    @BindView(R.id.textViewDetailoStateName)
    lateinit var textViewDetailoStateName: TextView
    @BindView(R.id.imageViewShipping)
    lateinit var imageViewShipping: ImageView
    @BindView(R.id.textViewNameSeller)
    lateinit var textViewNameSeller: TextView
    @BindView(R.id.textViewSeller)
    lateinit var textViewSeller: TextView
    @BindView(R.id.buttonMP)
    lateinit var buttonMP: Button
    @BindView(R.id.buttonBack)
    lateinit var buttonBack: TextView
    @BindView(R.id.textView1Quantity)
    lateinit var textView1Quantity: TextView

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

        detailProductsFragmentView = inflater.inflate(
            R.layout.fragment_detail_producto,
            container,
            false
        )
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
        buttonMP.setOnClickListener { v ->
            showDialog()

        }
        buttonBack.setOnClickListener { v ->
            //VOLVER A HOME
            (getActivity() as MainActivity2).goHome()
        }

    }
     fun setData(){
         if(detailProduct.condition.equals("new")){
             textViewState.text="Nuevo  | "
         }else if(detailProduct.condition.equals("used")){
             textViewState.text="Usado  | "
         }


         textViewCantVendidos.text=" ${detailProduct.sold_quantity!!.toInt().toString()} vendidos"
         textViewTitle.text="${detailProduct.title}"
         val formato = DecimalFormat("#,###")
         val valorFormateado: String = formato.format(detailProduct.price)
         textViewPrice.text="$${valorFormateado}"

         if(detailProduct.available_quantity!!.toInt().toString() == "1"){
             textView1Quantity.text=resources.getString(R.string.ultima_unidad)
             textViewAvailableQuantity.text=""
         }else{
             textViewAvailableQuantity.text="${detailProduct.available_quantity!!.toInt().toString()}"
         }
         if(detailProduct.accepts_mercadopago!!){
             buttonMP.visibility=View.VISIBLE
         }
         if(detailProduct.shipping!=null && detailProduct.shipping!!.mode.equals("me2")){
             textViewDetailoShipping.text=resources.getString(R.string.envio_con_normalidad)
         }
         else{
             imageViewShipping.setImageDrawable(resources.getDrawable(R.drawable.ic_envio_acordar))
             textViewDetailoShipping.text=resources.getString(R.string.envio_a_acordar)
         }
         if(detailProduct.shipping!=null && detailProduct.shipping!!.free_shipping!!){
             imageViewShipping.setImageDrawable(resources.getDrawable(R.drawable.ic_envio_free))
             textViewDetailoShippingFree.visibility=View.VISIBLE
         }

         if(detailProduct.seller!!.permalink!=null){
             var name:String
             val fechaArray: Array<String> =
                 detailProduct.seller!!.permalink.toString().split("/").toTypedArray()
             name = (fechaArray[fechaArray.size-1]).replace("+"," ")
             textViewNameSeller.text="${name}"
         }
         if(detailProduct.address!=null){
             textViewDetailocityName.text="${detailProduct.address!!.city_name}"
             textViewDetailoStateName.text="${detailProduct.address!!.state_name}"
         }


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

    private fun showDialog() {
        val layout = layoutInflater.inflate(R.layout.dialog_toast, null)
        val myToast = Toast(getActivity()!!.applicationContext)
        myToast.duration = Toast.LENGTH_SHORT
        myToast.setGravity(Gravity.BOTTOM, 0, 0)
        myToast.view = layout//setting the view of custom toast layout
        myToast.show()
    }
}