package com.example.dynamicrecyclerview

import android.os.Bundle

import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FieldAdapter
    private val fieldList = mutableListOf<List<String>>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Initial data
        fieldList.add(List(6) { "" })

        adapter = FieldAdapter(fieldList)
        recyclerView.adapter = adapter

        findViewById<ImageButton>(R.id.addButton).setOnClickListener {
            fieldList.add(List(6) { "" })
            adapter.notifyItemInserted(fieldList.size - 1)
        }
    }
}

