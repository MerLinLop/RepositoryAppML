package com.example.appml._view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.appml.R
import com.example.appml._model.local.historic_search.HistoricSearchEntity
import com.example.appml._view.adapters.WordSearchRecyclerViewAdapter
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import com.example.appml._viewmodel.SearchViewModel
import com.example.appml.utils.ResponseObjetBasic
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable
import java.util.*


class SearchFragment : BaseFragment(), BasicMethods {

    lateinit var searchFragmentView: View
    private lateinit var mContext: Context
    lateinit var mSearchViewModel: SearchViewModel
    var listSearch: MutableList<String> = ArrayList()
    lateinit var adapter: WordSearchRecyclerViewAdapter
    @BindView(R.id.recyclerViewSearch)
    lateinit var recyclerViewSearch: RecyclerView
    @BindView(R.id.searchEditText)
    lateinit var searchEditText: EditText
    @BindView(R.id.imageViewSearch)
    lateinit var imageViewSearch: ImageView
    @BindView(R.id.imageViewClose)
    lateinit var imageViewClose: ImageView



    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchFragmentView = inflater.inflate(R.layout.activity_search, container, false)
        ButterKnife.bind(this, searchFragmentView)

        initObservables()
        init()
        initListeners()

        return searchFragmentView
    }

    override fun initObservables() {

        //INCIALIZA VIEWMODEL
        mSearchViewModel = ViewModelProviders.of(this).get(
            SearchViewModel::class.java
        )
        //PIDE LA LISTA DE TODAS LAS PALABRAS QUE FUERON BUSCADAS
        mSearchViewModel.dataHistoricSearch()
    }

    override fun init() {
        (getActivity() as MainActivity2).configurarBarra(true)
       // (activity as MainActivity2?)!!.configurarBarra(true)
        //ESCUCHADOR DE LAS PALABRAS BUSCADAS
        mSearchViewModel.listHistoricSearch.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { entity: List<HistoricSearchEntity>  ->
                listSearch.clear()
                for (historic in entity) {
                    listSearch.add(historic.palabra.toLowerCase())
                }
                setAdapter()
            }
        )

    }
    fun getproducts(word:String){

        val bundle = Bundle()
        bundle.putString("word", word)
        Navigation.findNavController(searchFragmentView)
            .navigate(R.id.action_searchFragment_to_productsFragment, bundle)

    }
    override fun initListeners() {
        searchEditText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    //BAJAR EL TECLADO AL BUSCAR

                    val word= searchEditText.text.toString().toLowerCase()
                    mSearchViewModel.insertSearch(word)

                    getproducts(searchEditText.text.toString())
                    true
                }
                else -> false
            }
        }
        imageViewSearch.setOnClickListener { v ->

            val word= searchEditText.text.toString().toLowerCase()
            mSearchViewModel.insertSearch(word)
            getproducts(searchEditText.text.toString())
        }
        imageViewClose.setOnClickListener { v ->
            (getActivity() as MainActivity2).onBackPressed()
        }
    }

    fun setAdapter(){
        recyclerViewSearch.layoutManager = LinearLayoutManager(activity)
        recyclerViewSearch.setHasFixedSize(true)
        adapter = mContext.let {
            WordSearchRecyclerViewAdapter(it, listSearch)
        }
        recyclerViewSearch.adapter = adapter

        adapter.setListner(object :
            WordSearchRecyclerViewAdapter.WordSearchRecyclerViewAdapterListener {
            override fun onClick(word: String) {
                getproducts(word)
            }
        })
    }

}
