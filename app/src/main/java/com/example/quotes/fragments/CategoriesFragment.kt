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
import com.example.quotes.adapters.CategoriesAdapter
import com.example.quotes.models.CategoryModel
import com.example.quotes.widgets.LeftSpacingDecoration
import kotlinx.android.synthetic.main.fragment_categories.*
import org.json.JSONException


class CategoriesFragment : Fragment() {
    private val categories: MutableList<CategoryModel> = mutableListOf()
    private val url: String =
        "https://raw.githubusercontent.com/WaadSulaiman/Data/master/Categories"
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout_fragment_categories_recyclerView.apply {
            loadData()
            addItemDecoration(LeftSpacingDecoration(30))
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    private fun loadData() {
        categories.clear()
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
            try {
                val categoriesArray = it.getJSONArray("Category")
                for (index in 0 until categoriesArray.length()) {
                    val currentObject = categoriesArray.getJSONObject(index)
                    val category = CategoryModel(
                        currentObject.getString("Category"),
                        currentObject.getString("url")
                    )
                    categories.add(category)
                }
            } catch (event: JSONException) {
            }
            categories.shuffle()
            categoriesAdapter = CategoriesAdapter(categories)
            layout_fragment_categories_recyclerView.adapter = categoriesAdapter

        }, {

        }
        )
        Volley.newRequestQueue(context).add(request)
    }
}