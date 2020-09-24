package com.example.quotes.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.quotes.R
import com.example.quotes.data.SampleSQLiteDBHelper
import com.google.android.material.snackbar.Snackbar

class OwnCursorCustomAdapter(context: Context, cursor: Cursor) : CursorAdapter(context, cursor) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.quote_recycler_view_item_content, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val id = cursor?.getLong(0)
        val database: SampleSQLiteDBHelper = SampleSQLiteDBHelper(context)
        val quoteTextView: TextView? = view?.findViewById(R.id.quote_text)
        val quoteTextAuthor: TextView? = view?.findViewById(R.id.author_text)
        val cardView: CardView? = view?.findViewById(R.id.card_view_front)
        cardView?.setBackgroundResource(R.drawable.favorite_card_background)

        quoteTextView?.text = cursor?.getString(1)
        quoteTextAuthor?.text = cursor?.getString(2)
        cardView?.setOnLongClickListener {
            val builder = AlertDialog.Builder(view.context).setTitle("Delete").setMessage(
                "Are you sure you want to delete this Quote?"
            ).setPositiveButton(R.string.yes_choice) { dialog, which ->
                Snackbar.make(view, "Quote deleted.", Snackbar.LENGTH_SHORT).show()
                database.removeFromTableOwn(id!!.toInt())
                swapCursor(SampleSQLiteDBHelper(view.context).readFromDBTableOwn())
                dialog.dismiss()
            }.setNegativeButton(R.string.no_choice) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }
            val alertDialog = builder.create()
            alertDialog.show()
            val buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            val buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)

            buttonNegative.setTextColor(Color.BLACK)
            buttonPositive.setTextColor(Color.BLACK)

            true
        }
    }

}