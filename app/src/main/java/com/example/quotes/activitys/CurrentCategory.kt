package com.example.quotes.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.quotes.R
import com.example.quotes.TopSpacingDecoration
import com.example.quotes.adapters.CategoryAdapter
import com.example.quotes.models.QuoteModel
import kotlinx.android.synthetic.main.activity_current_category.*
import org.json.JSONException

class CurrentCategory : AppCompatActivity() {
    private val dataQuotes: MutableList<QuoteModel> = mutableListOf()
    private var url: String? = null
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_category)

        layout_current_category_textView_title.text = intent.getStringExtra("Category")
        url = intent.getStringExtra("url")
        current_category_recycler_view.apply {
            loadData()
            adapter = CategoryAdapter(dataQuotes)
            layoutManager = LinearLayoutManager(this@CurrentCategory)
            addItemDecoration(TopSpacingDecoration(30))
        }

    }

    private fun loadData() {
        dataQuotes.clear()
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
            try {
                val quotesArray = it.getJSONArray("Quotes")
                for (index in 0 until quotesArray.length()) {
                    val currentObject = quotesArray.getJSONObject(index)
                    val quote = QuoteModel(
                        currentObject.getString("Author"),
                        currentObject.getString("Quotes")
                    )
                    dataQuotes.add(quote)
                }
            } catch (event: JSONException) {
            }
            dataQuotes.shuffle()
            adapter = CategoryAdapter(dataQuotes)
            current_category_recycler_view.adapter = adapter

        }, {

        }
        )
        Volley.newRequestQueue(this).add(request)
    }

}