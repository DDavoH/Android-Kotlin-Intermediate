package com.davoh.list_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class ListAdapter(private val context: Context,
                  private val listItems: MutableList<String>
):
        RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
       return MainViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
       )}

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(listItems[position], position)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    //se cierra al cerrarse el Adapter
    inner class MainViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
        override fun bind(item: String, position: Int) {
            itemView.textView.text = listItems[position]
        }
    }



}