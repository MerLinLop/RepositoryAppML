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
import com.example.appml._model.remote.Attribute

class AttributesRecyclerViewAdapter (var context: Context,
                                     var listAtribute: List<Attribute>
) :
    RecyclerView.Adapter<AttributesRecyclerViewAdapter.AttributesViewHolder>(){

    var mInflater: LayoutInflater

    class AttributesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewNameAttribute: TextView
        var textViewValueNameAttribute: TextView
        init {
            textViewNameAttribute = itemView.findViewById(R.id.textViewNameAttribute)
            textViewValueNameAttribute = itemView.findViewById(R.id.textViewValueNameAttribute)

        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttributesViewHolder {
        val itemView = mInflater.inflate(R.layout.item_attribute, parent, false)

        //itemView.setOnClickListener(this);
        return AttributesViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: AttributesViewHolder, position: Int) {
        val word = listAtribute[position]

        holder.textViewNameAttribute.text = "${word.name}"
        holder.textViewValueNameAttribute.text = "${word.value_name}"


    }
    override fun getItemCount(): Int {
        return listAtribute.size
    }

}