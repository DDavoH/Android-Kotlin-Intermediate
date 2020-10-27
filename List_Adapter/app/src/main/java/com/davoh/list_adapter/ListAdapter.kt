package com.davoh.list_adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val context: Context,
                  private val listItems: List<String>,
                  private val itemClickListener: AdapterView.OnItemClickListener):
        RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}