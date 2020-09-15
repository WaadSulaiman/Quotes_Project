package com.example.quotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.quotes.R
import com.example.quotes.TopSpacingDecoration
import com.example.quotes.adapters.CustomAdapter
import com.example.quotes.models.QuoteModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomeFragment() : Fragment() {
    private val dataQuotes: MutableList<QuoteModel> = mutableListOf()
    private val url = "https://raw.githubusercontent.com/WaadSulaiman/Data/master/Data"
    private lateinit var adapter: CustomAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        layout_fragment_home_recyclerView.apply {
            setHasFixedSize(true)
            fetchData()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(TopSpacingDecoration(30))
            adapter = adapter
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun fetchData() {
        val request: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            try {
                dataQuotes.clear()
                val jsonArray: JSONArray = it.getJSONArray("Quotes")
                for (jsonObjectIndex in 0 until jsonArray.length()) {
                    val currentJsonObject: JSONObject = jsonArray.getJSONObject(jsonObjectIndex)
                    val quote: QuoteModel = QuoteModel(
                        author = currentJsonObject.getString("quoteAuthor"),
                        quoteText = currentJsonObject.getString("quoteText")
                    )
                    dataQuotes.add(quote)
                }
                dataQuotes.shuffle()
                adapter = CustomAdapter(dataQuotes)
                layout_fragment_home_recyclerView.adapter = adapter

            } catch (event: JSONException) {
            }
        }, {
            {
            }
        }
        )
        val requestQueue = Volley.newRequestQueue(context).add(request)
    }
}