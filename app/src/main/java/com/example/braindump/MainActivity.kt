package com.example.braindump

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listAdaptor: ListAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdaptor = ListAdaptor(mutableListOf())

        rvBraindumpList.adapter = listAdaptor
        rvBraindumpList.layoutManager = LinearLayoutManager(this)

        btnBraindumpAdd.setOnClickListener {
            val listTitle = etBraindumpTitle.text.toString()
            if (listTitle.isNotEmpty()){
                val list = List(listTitle)
                listAdaptor.addList(list)
                etBraindumpTitle.text.clear()
            }
        }
        btnBraindumpDelete.setOnClickListener {
            listAdaptor.deleteList()
        }
    }
}