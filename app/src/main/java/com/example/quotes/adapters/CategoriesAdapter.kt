package com.example.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.R
import com.example.quotes.models.CategoryModel

class CategoriesAdapter(private val categories: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val textView: TextView = item.findViewById(R.id.text_view)
        fun bindItem(category: CategoryModel) {
            textView.text = category.categoryText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_recycler_view_item_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}