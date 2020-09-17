package com.example.quotes.fragments

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quotes.R
import com.example.quotes.adapters.CursorCustomAdapter
import com.example.quotes.data.SampleSQLiteDBHelper
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cursor: Cursor = SampleSQLiteDBHelper(view.context).readFromDB()
        listView.adapter = CursorCustomAdapter(view.context, cursor)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}