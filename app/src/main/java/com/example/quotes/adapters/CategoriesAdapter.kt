package com.example.quotes.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.R
import com.example.quotes.activitys.CurrentCategory
import com.example.quotes.models.CategoryModel

class CategoriesAdapter(private val categories: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val cardView: CardView = item.findViewById(R.id.category_card_view)
        private val textView: TextView = item.findViewById(R.id.text_view)
        fun bindItem(category: CategoryModel) {
            textView.text = category.categoryText
            cardView.setOnClickListener {
                val intent: Intent = Intent(it.context, CurrentCategory::class.java)
                intent.putExtra("Category", category.categoryText)
                intent.putExtra("url", category.url)
                startActivity(it.context, intent, null)
            }
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