package com.example.dynamicrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

class FieldAdapter(private val fields: MutableList<List<String>>) :
    RecyclerView.Adapter<FieldAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val editTexts = listOf(
            view.findViewById(R.id.editText1),
            view.findViewById(R.id.editText2),
            view.findViewById(R.id.editText3),
            view.findViewById(R.id.editText4),
            view.findViewById(R.id.editText5),
            view.findViewById<EditText>(R.id.editText6)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_field, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowData = fields[position]
        holder.editTexts.forEachIndexed { index, editText ->
            editText.setText(rowData[index])
        }
    }

    override fun getItemCount() = fields.size
}