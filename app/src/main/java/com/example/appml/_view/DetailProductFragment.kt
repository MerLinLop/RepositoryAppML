package com.example.appml._view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appml.R
import com.example.appml._model.remote.Results
import com.example.appml._model.remote.product.DescriptionsProduct
import com.example.appml._model.remote.product.ProductServer
import com.example.appml._view.adapters.AttributesRecyclerViewAdapter
import com.example.appml._view.adapters.DestacadoViewPagerAdapter
import com.example.appml._view.adapters.WordSearchRecyclerViewAdapter
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import com.example.appml._viewmodel.HomeViewModel
import com.example.appml._viewmodel.SearchViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator
import java.text.DecimalFormat


class DetailProductFragment: BaseFragment(), BasicMethods {


    lateinit var detailProductsFragmentView: View
    private lateinit var mContext: Context
    lateinit var mSearchViewModel: SearchViewModel


    @BindView(R.id.baseOverlayProgress)
    lateinit var baseOverlayProgress: FrameLayout
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
    @BindView(R.id.textView1Quantity)
    lateinit var textView1Quantity: TextView
    @BindView(R.id.textViewTextDescription)
    lateinit var textViewTextDescription: TextView

    lateinit var adapterDestacado: DestacadoViewPagerAdapter
    @BindView(R.id.viewPagerPictures)
    lateinit var viewPagerPictures: ViewPager
    @BindView(R.id.circleIndicatorPictures)
    lateinit var circleIndicatorPictures: CircleIndicator

    lateinit var adapterAtributos: AttributesRecyclerViewAdapter
    @BindView(R.id.recyclerViewCaracteristicas)
    lateinit var recyclerViewCaracteristicas: RecyclerView


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
        mSearchViewModel = ViewModelProviders.of(this).get(
            SearchViewModel::class.java
        )


        mSearchViewModel.liveDataItemProducts.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { results: ProductServer ->
                Log.d(TAG, results.toString())
                setDataExtra(results)
            }
        )
        mSearchViewModel.liveDataDescriptionsProduct.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { results: DescriptionsProduct ->
                Log.d(TAG, results.toString())
                textViewTextDescription.text=results.plain_text
            }
        )
    }

    override fun init() {

        val bundle = arguments
        if (bundle != null) {
            detailProduct= bundle.getSerializable("product") as Results
            //OBTENER ATRIBUTOS E IMAGENES
            GlobalScope.launch { detailProduct.id?.let { mSearchViewModel.getItemProduct(it) } }
            //OBTENER DESCRIPCION DEL PRODUCTO
            GlobalScope.launch { detailProduct.id?.let { mSearchViewModel.getDescriptionsProduct(it) } }
            setData()

        }
    }

    override fun initListeners() {
        buttonMP.setOnClickListener { v ->
            showDialog()

        }

    }

    fun setDataExtra(results: ProductServer){
        adapterDestacado = DestacadoViewPagerAdapter()
        viewPagerPictures.adapter = adapterDestacado
        circleIndicatorPictures.setViewPager(viewPagerPictures)
        adapterDestacado.registerDataSetObserver(circleIndicatorPictures.dataSetObserver)
        val listPictures: MutableList<String> = mutableListOf()
        if(results.body!=null && !results.body!!.pictures.isNullOrEmpty()){
            for(pictures in results.body!!.pictures!!){
                listPictures.add(pictures.url.toString())
            }
        }
        if(listPictures.isNotEmpty()){
            adapterDestacado.add(listPictures)
        }
        recyclerViewCaracteristicas.layoutManager = LinearLayoutManager(activity)
        recyclerViewCaracteristicas.setHasFixedSize(true)


        adapterAtributos = mContext.let {
            AttributesRecyclerViewAdapter(it, results.body!!.attributes!!)
        }
        recyclerViewCaracteristicas.adapter = adapterAtributos

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


         /*Glide.with(mContext)
             .load(Uri.parse(detailProduct.thumbnail))
             .apply(
                 RequestOptions()
                     .skipMemoryCache(false)
                     .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                     .fitCenter()
             )
             .into(imageViewProductDetail)*/
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