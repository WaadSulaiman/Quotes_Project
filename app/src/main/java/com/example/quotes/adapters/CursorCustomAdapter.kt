package com.example.quotes.adapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.quotes.R
import com.example.quotes.data.SampleSQLiteDBHelper
import com.google.android.material.snackbar.Snackbar

class CursorCustomAdapter(context: Context, cursor: Cursor) : CursorAdapter(context, cursor) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.quote_recycler_view_item_content, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val quoteTextView: TextView? = view?.findViewById(R.id.quote_text)
        val quoteTextAuthor: TextView? = view?.findViewById(R.id.author_text)
        val cardView: CardView? = view?.findViewById(R.id.card_view_front)
        val listView: ListView? = view?.findViewById(R.id.listView)
        cardView?.setBackgroundResource(R.drawable.categories_background)
        quoteTextView?.text = cursor?.getString(1)
        quoteTextAuthor?.text = cursor?.getString(2)
        cardView?.setOnLongClickListener {
            SampleSQLiteDBHelper(it.context).delete(cursor?.getLong(0)!!)
            Snackbar.make(view, "Quote successfully deleted.", Snackbar.LENGTH_SHORT).show()
            true
        }
    }

}