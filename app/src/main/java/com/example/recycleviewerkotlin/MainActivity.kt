package com.example.recycleviewerkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var dataList:ArrayList<DataClass>
    private  lateinit var searchList:ArrayList<DataClass>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nameList = arrayListOf("Jack","John","Rahim","Karim","Rahul","Rubel","Jack","John","Rahim","Karim","Rahul","Rubel","Jack","John","Rahim","Karim","Rahul","Rubel","Jack","John","Rahim","Karim","Rahul","Rubel","Jack","John","Rahim","Karim","Rahul","Rubel","Jack","John","Rahim","Karim","Rahul","Rubel")
        var addressList = arrayListOf("Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate","Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate","Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate","Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate","Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate","Dhaka","Barista","Maharajahs","Ranger","Dinosaur","Farm gate")
        var ageList = arrayListOf(21,22,23,24,25,26,21,22,23,24,25,26,21,22,23,24,25,26,21,22,23,24,25,26,21,22,23,24,25,26,21,22,23,24,25,26)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        for(i in nameList.indices){
            dataList.add(DataClass(nameList[i],addressList[i],ageList[i],"",true))
        }
        searchList.addAll(dataList)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = AdapterClass(searchList)

        var searchView = findViewById<SearchView>(R.id.searchView)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return  true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    dataList.forEach{
                        if(it.name.lowercase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })



        var button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            for(x in dataList){
                if (!x.textField.isNullOrEmpty()){
                    println(x.name+","+x.address+","+x.textField+","+x.check)
                }
            }
        }
    }
}