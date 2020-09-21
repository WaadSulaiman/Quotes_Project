package com.example.quotes.fragments

import android.app.Activity
import android.database.Cursor
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.quotes.R
import com.example.quotes.adapters.OwnCursorCustomAdapter
import com.example.quotes.data.SampleSQLiteDBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create.*


class CreateFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cursor: Cursor = SampleSQLiteDBHelper(view.context).readFromDBTableOwn()
        list_view_own_quotes.adapter = OwnCursorCustomAdapter(view.context, cursor)

        floating.setOnClickListener {
            floating.visibility = FloatingActionButton.GONE
            cardView.visibility = CardView.VISIBLE
            list_view_own_quotes.visibility = ListView.GONE
            cardView.elevation = 100F
            addButton.elevation = 20F
            cancelButton.elevation = 20F
        }

        text_quote_input.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                text_quote_input.backgroundTintMode = PorterDuff.Mode.DARKEN
            }
        }
        text_author_input.setOnFocusChangeListener { v, hasFocus ->
            text_author_input.backgroundTintMode = PorterDuff.Mode.DARKEN
        }

        cancelButton.setOnClickListener {
            floating.visibility = FloatingActionButton.VISIBLE
            cardView.visibility = CardView.GONE
            list_view_own_quotes.visibility = ListView.VISIBLE
            val imm: InputMethodManager =
                context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            text_quote_input.text.clear()
            text_author_input.text.clear()
            Snackbar.make(it, "Quote creation canceled!", Snackbar.LENGTH_SHORT).show()

        }
        addButton.setOnClickListener {
            if (text_quote_input.text.isEmpty() || text_author_input.text.isEmpty()) {
                Snackbar.make(it, "Please fill the text fields.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val database: SampleSQLiteDBHelper = SampleSQLiteDBHelper(it.context)
            database.saveToDBTableOwn(
                text_quote_input.text.toString(),
                text_author_input.text.toString()
            )
            floating.visibility = FloatingActionButton.VISIBLE
            cardView.visibility = CardView.GONE
            list_view_own_quotes.visibility = ListView.VISIBLE
            Snackbar.make(it, "Quote created, and saved locally. On next refresh, quote will show.", Snackbar.LENGTH_SHORT).show()

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create, container, false)
    }
}