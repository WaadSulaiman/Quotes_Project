package com.example.quotes.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.quotes.R
import com.example.quotes.TopSpacingDecoration
import com.example.quotes.adapters.CategoryAdapter
import com.example.quotes.models.QuoteModel
import kotlinx.android.synthetic.main.activity_current_category.*
import org.json.JSONException
import org.json.JSONObject

class CurrentCategory : AppCompatActivity() {
    private val dataQuotes: MutableList<QuoteModel> = mutableListOf()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_category)
        Log.d("URL", "hello")
        layout_current_category_textView_title.text = intent.getStringExtra("Category")

        layout_current_category_recycler_view.apply {
            fetchData(intent.getStringExtra("url")!!)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(TopSpacingDecoration(30))
            adapter = adapter
        }

    }

    private fun fetchData(url: String) {
        Log.d("testt", "test")
        val request: JsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, {
            try {
                dataQuotes.clear()
                for (jsonObjectIndex in 0 until it.length()) {
                    val currentJsonObject: JSONObject = it.getJSONObject(jsonObjectIndex)
                    val quote: QuoteModel = QuoteModel(
                        author = currentJsonObject.getString("Author"),
                        quoteText = currentJsonObject.getString("Quotes")
                    )
                    //Log.d("testt", currentJsonObject.getString("Author"))
                    dataQuotes.add(quote)
                }
                dataQuotes.shuffle()
                adapter = CategoryAdapter(dataQuotes)
                layout_current_category_recycler_view.adapter = adapter

            } catch (event: JSONException) {
                Log.d("eventJson", event.message)
            }
        }, {
            {
            }
        }
        )
        Volley.newRequestQueue(this).add(request)
    }
}