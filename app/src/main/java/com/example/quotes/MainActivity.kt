package com.example.quotes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quotes.fragments.CategoriesFragment
import com.example.quotes.fragments.FavoriteFragment
import com.example.quotes.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(nabListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
        }
    }

    private val nabListener = BottomNavigationView.OnNavigationItemSelectedListener {
        var selectedFragment: Fragment? = null

        when (it.itemId) {
            R.id.quote_page -> selectedFragment = HomeFragment()
            R.id.category_page -> selectedFragment = CategoriesFragment()
            R.id.favorite_page -> selectedFragment = FavoriteFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment!!).commit()
        true
    }

}