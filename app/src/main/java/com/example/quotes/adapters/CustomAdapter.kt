package com.example.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.R
import com.example.quotes.data.SampleSQLiteDBHelper
import com.example.quotes.models.QuoteModel
import com.google.android.material.snackbar.Snackbar


class CustomAdapter(private val quoteList: MutableList<QuoteModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_recycler_view_item_content, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(quoteList[position])

    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val db: SampleSQLiteDBHelper = SampleSQLiteDBHelper(itemView.context)
        private val cardView: CardView = itemView.findViewById(R.id.card_view_front)
        private val textViewQuote: TextView = itemView.findViewById(R.id.quote_text)
        private val textViewAuthor: TextView = itemView.findViewById(R.id.author_text)
        fun bindItems(quote: QuoteModel) {
            textViewQuote.text = quote.quoteText
            if (quote.author.isEmpty()) {
                textViewAuthor.setText(R.string.by_unknown)
            } else {
                textViewAuthor.text = quote.author
            }

            cardView.setOnLongClickListener {
                db.saveToDB(quote.quoteText, quote.author)
                Snackbar.make(itemView, "Quote added to favorites.", Snackbar.LENGTH_SHORT).show()
                true
            }
        }

    }
}
