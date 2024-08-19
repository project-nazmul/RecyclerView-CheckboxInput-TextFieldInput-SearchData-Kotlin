package com.example.recycleviewerkotlin

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class AdapterClass(private  val dataList:ArrayList<DataClass>):RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_list_item,parent,false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        val currentItem = dataList[position]
        holder.sl.text = (position+1).toString()
        holder.userName.text = currentItem.name
        holder.userAddress.text = currentItem.address
        holder.userAge.text = currentItem.age.toString()
        holder.choice.setText(currentItem.textField)
        holder.checkBox.isChecked = currentItem.check

    }

    inner class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        var sl = itemView.findViewById<TextView>(R.id.sl)
        var userName = itemView.findViewById<TextView>(R.id.userName)
        var userAddress = itemView.findViewById<TextView>(R.id.userAddress)
        var userAge = itemView.findViewById<TextView>(R.id.userAge)
        var choice = itemView.findViewById<EditText>(R.id.choice)
        var checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)


        init {
            itemView.setOnClickListener(View.OnClickListener {
                //Snackbar.make(itemView,userName.text.toString(),Snackbar.LENGTH_SHORT).show()
                Toast.makeText(itemView.context, userName.text.toString(), Toast.LENGTH_SHORT).show()
            })
            checkBox.setOnClickListener {v ->
                val isChecked = (v as CheckBox).isChecked
                dataList[adapterPosition].check = isChecked
            }

            choice.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    dataList[adapterPosition].textField = s.toString()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            })
        }

    }
}