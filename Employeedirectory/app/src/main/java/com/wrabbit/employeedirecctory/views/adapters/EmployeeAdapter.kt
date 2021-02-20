package com.wrabbit.employeedirecctory.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrabbit.employeedirecctory.R
import com.wrabbit.employeedirecctory.model.models.Employee

public class EmployeeAdapter(private val onClickListener: (Employee) -> Unit):ListAdapter<Employee,EmployeeViewHolder>(EmployeeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.list_item_employee,parent,false)
        return EmployeeViewHolder(view,onClickListener)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.item =getItem(position)
    }

}

class EmployeeViewHolder(private val view: View,private val onClickListener:(Employee)-> Unit):RecyclerView.ViewHolder(view) {
    var item:Employee?=null
        set(value){
            value?.let { item->
                field = item
                view.setOnClickListener {onClickListener(item) }
                view.findViewById<TextView>(R.id.textViewName).text = item.name
                view.findViewById<TextView>(R.id.textViewId).text = item.company?.companyname
                Glide.with(view.context).load(item.profile_image).placeholder(R.drawable.ic_person).into(view.findViewById<ImageView>(R.id.imageview_avatar))
            }
        }
}

class EmployeeDiffCallback:DiffUtil.ItemCallback<Employee>(){
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id ==newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }

}