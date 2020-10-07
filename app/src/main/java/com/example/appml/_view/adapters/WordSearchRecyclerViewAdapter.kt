package com.example.appml._view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appml.R

class WordSearchRecyclerViewAdapter (var context: Context,
                                    var listWordSearch: List<String>
) :
    RecyclerView.Adapter<WordSearchRecyclerViewAdapter.WordViewHolder>(){

    var mInflater: LayoutInflater
    lateinit var listener: WordSearchRecyclerViewAdapterListener

    class WordViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewWordSearch: TextView

        init {
            textViewWordSearch = itemView.findViewById(R.id.textViewWordSearch)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.item_search, parent, false)

        //itemView.setOnClickListener(this);
        return WordViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = listWordSearch[position]

        holder.textViewWordSearch.text = "${word}"


        holder.itemView.setOnClickListener {
            listener.onClick(word)
        }


    }
    override fun getItemCount(): Int {
        return listWordSearch.size
    }

    fun setListner(listener: WordSearchRecyclerViewAdapterListener) {
        this.listener = listener
    }

    interface WordSearchRecyclerViewAdapterListener {
        fun onClick(word: String)

    }
}