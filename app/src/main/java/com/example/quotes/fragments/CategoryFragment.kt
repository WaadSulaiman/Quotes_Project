package com.example.quotes.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.quotes.LeftSpacingDecoration
import com.example.quotes.R
import com.example.quotes.adapters.CustomAdapterCategory
import com.example.quotes.models.CategoryModel
import kotlinx.android.synthetic.main.fragment_category.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CategoryFragment() : Fragment() {
    private val dataCategory: MutableList<CategoryModel> = mutableListOf()
    private val url = "https://raw.githubusercontent.com/WaadSulaiman/Data/master/Categories"
    private lateinit var adapter: CustomAdapterCategory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        layout_fragment_category_recycler_view.apply {
            setHasFixedSize(true)
            fetchData()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(LeftSpacingDecoration(30))
            adapter = CustomAdapterCategory(dataCategory)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }


    private fun fetchData() {
        val request: JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            try {
                dataCategory.clear()
                val jsonArray: JSONArray = it.getJSONArray("Category")
                for (jsonObjectIndex in 0 until jsonArray.length()) {
                    val currentJsonObject: JSONObject = jsonArray.getJSONObject(jsonObjectIndex)
                    val category: CategoryModel = CategoryModel(
                        categoryText = currentJsonObject.getString("Category"),
                        url = currentJsonObject.getString("url")
                    )
                    dataCategory.add(category)
                }

                Log.d("Array", dataCategory.size.toString())
                dataCategory.shuffle()
                adapter = CustomAdapterCategory(dataCategory)
                layout_fragment_category_recycler_view.adapter = adapter
                adapter.notifyDataSetChanged()

            } catch (event: JSONException) {
            }
        }, {
            {
            }
        }
        )
        val requestQueue = Volley.newRequestQueue(context).add(request)
        //currentAdapter.notifyDataSetChanged()
    }
}