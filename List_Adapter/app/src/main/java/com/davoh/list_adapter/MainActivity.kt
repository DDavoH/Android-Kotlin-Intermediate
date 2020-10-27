package com.davoh.list_adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val myMap: Map<Int,String> = mapOf(0 to "Item 0", 1 to "Item 1", 2 to "Item 2", 3 to "Item 3")
        val listItems : List<String> = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")


        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        rv.adapter = ListAdapter(this, listItems)
    }



}