package com.example.appml._view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.appml.R
import com.example.appml._model.remote.Results
import com.example.appml._view.adapters.ProductsRecyclerViewAdapter
import com.example.appml._view.adapters.WordSearchRecyclerViewAdapter
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import com.example.appml._viewmodel.SearchViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable
import java.util.ArrayList

class ProductsFragment: BaseFragment(), BasicMethods {

    lateinit var productsFragmentView: View
    private lateinit var mContext: Context
    lateinit var mSearchViewModel: SearchViewModel
    var word: String? = null

    @BindView(R.id.baseOverlayProgress)
    lateinit var baseOverlayProgress: FrameLayout
    @BindView(R.id.recyclerViewProducts)
    lateinit var recyclerViewProducts: RecyclerView
    @BindView(R.id.constraintLayoutSinDatos)
    lateinit var constraintLayoutSinDatos: ConstraintLayout
    @BindView(R.id.textViewCantProduct)
    lateinit var textViewCantProduct: TextView
    @BindView(R.id.constraintLayoutToolbar)
    lateinit var constraintLayoutToolbar: ConstraintLayout
    lateinit var adapter: ProductsRecyclerViewAdapter
    var listProduct: MutableList<Results> = ArrayList()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productsFragmentView = inflater.inflate(R.layout.fragment_products, container, false)
        ButterKnife.bind(this, productsFragmentView)

        initObservables()
        init()
        initListeners()

        return productsFragmentView
    }

    override fun initObservables() {
        (getActivity() as MainActivity2).configurarBarra(false)
       // (activity as MainActivity2?)!!.configurarBarra(false)
        mSearchViewModel = ViewModelProviders.of(this).get(
            SearchViewModel::class.java
        )

        mSearchViewModel.liveDataListProducts.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { results: List<Results> ->
                Log.d(TAG, results.toString())
                if(!results.isNullOrEmpty()){
                    listProduct= results as MutableList<Results>
                    setAdapter()
                    baseOverlayProgress.visibility = View.GONE
                }
                else{
                    baseOverlayProgress.visibility = View.GONE
                    constraintLayoutSinDatos.visibility=View.VISIBLE
                }
            }
        )
    }

    override fun init() {
        val bundle = arguments
        if (bundle != null) {
            word= bundle.getString("word")
            if(!word.isNullOrBlank()){
                baseOverlayProgress.visibility = View.VISIBLE
                baseOverlayProgress.bringToFront()
                GlobalScope.launch { mSearchViewModel.seachProduct(word!!) }
            }
            else{
                constraintLayoutToolbar.visibility = View.GONE
                baseOverlayProgress.visibility = View.GONE
                constraintLayoutSinDatos.visibility=View.VISIBLE
                //Toast.makeText(mContext,"SIN PALABRAS",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun initListeners() {

    }

    fun setAdapter(){
        constraintLayoutToolbar.visibility = View.VISIBLE
        textViewCantProduct.text="${listProduct.size.toString()} resultados"
        recyclerViewProducts.layoutManager = LinearLayoutManager(activity)
        recyclerViewProducts.setHasFixedSize(true)
        adapter = mContext.let {
            ProductsRecyclerViewAdapter(it, listProduct)
        }
        recyclerViewProducts.adapter = adapter

        adapter.setListner(object :
            ProductsRecyclerViewAdapter.ProductsRecyclerViewAdapterListener {
            override fun onClick(product: Results) {
              detailProducto(product)
            }
        })
    }
    fun detailProducto(product:Results){

        val bundle = Bundle()
        bundle.putSerializable("product", product as Serializable)
        Navigation.findNavController(productsFragmentView)
            .navigate(R.id.action_productsFragment_to_detailProductsFragment, bundle)

    }

}