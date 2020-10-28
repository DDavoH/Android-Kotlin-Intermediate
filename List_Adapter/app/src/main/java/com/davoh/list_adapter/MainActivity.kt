package com.davoh.list_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listItems = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        for (x in 6..10){
            listItems.add("Item $x")
        }


        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val adapter = RvListAdapter()
        rv.adapter = adapter
        adapter.submitList(listItems)

        fab.setOnClickListener {
            val newList = ArrayList<String>()
            newList.addAll(listItems)
            val newItemValue = listItems.size + 1
            newList.add("Item $newItemValue")

            listItems.clear()
            listItems.addAll(newList)

            adapter.submitList(newList)

            Log.d("Debugeando", "onClick")
        }
    }



}