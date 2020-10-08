package com.example.appml._view

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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
            androidx.lifecycle.Observer { entity: List<HistoricSearchEntity> ->
                listSearch.clear()
                for (historic in entity.reversed()) {
                    listSearch.add(historic.palabra.toLowerCase())
                }
                setAdapter()
            }
        )

    }
    fun getproducts(word: String){
        val imm = (getActivity() as MainActivity2).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(searchEditText.windowToken, 0)
        (getActivity() as MainActivity2).setSearch(word,false)
        val bundle = Bundle()
        bundle.putString("word", word)
        Navigation.findNavController(searchFragmentView)
            .navigate(R.id.action_searchFragment_to_productsFragment, bundle)

    }
    override fun initListeners() {


        searchEditText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if(validateWordSearch()){
                        val word = searchEditText.text.toString().toLowerCase()
                        mSearchViewModel.insertSearch(word)

                        getproducts(searchEditText.text.toString())
                    }
                    true
                }
                else -> false
            }
        }
        imageViewSearch.setOnClickListener { v ->
                if(validateWordSearch()){
                    val word= searchEditText.text.toString().toLowerCase()
                    mSearchViewModel.insertSearch(word)
                    getproducts(searchEditText.text.toString())
                }

        }
        imageViewClose.setOnClickListener { v ->
            (getActivity() as MainActivity2).onBackPressed()
        }
        searchEditText.requestFocus() //Asegurar que editText tiene focus
        val imm = (getActivity() as MainActivity2).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)

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

    fun validateWordSearch(): Boolean{
        return if (TextUtils.isEmpty(searchEditText.text.toString())) {
            searchEditText.requestFocus()
            //textViewRequeridoTelefonoUsuaio.setVisibility(View.VISIBLE);
            searchEditText.error =
                mContext.resources.getString(R.string.error_field)
            false
    }else true
    }
}
