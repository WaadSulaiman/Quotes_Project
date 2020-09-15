package com.example.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.R
import com.example.quotes.models.CategoryModel

class CustomAdapterCategory(private val categoryList: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CustomAdapterCategory.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomAdapterCategory.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_recycler_view_item_content, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CustomAdapterCategory.ViewHolder, position: Int) {
        holder.bindItems(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text_view)
        fun bindItems(category: CategoryModel) {
            textView.text = category.categoryText
        }

    }

}