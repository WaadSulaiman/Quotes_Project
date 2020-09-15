package com.example.quotes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quotes.fragments.CategoryFragment
import com.example.quotes.fragments.FavoriteFragment
import com.example.quotes.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val categoryFragment = CategoryFragment()
        val favoriteFragment = FavoriteFragment()

        makeCurrentFragment(homeFragment)

        layout_main_activity_bottom_navigation_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.quote_page -> makeCurrentFragment(homeFragment)
                R.id.category_page -> makeCurrentFragment(categoryFragment)
                R.id.favorite_page -> makeCurrentFragment(favoriteFragment)
            }
            true

        }
    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_main_activity_frame_layout, fragment)
            commit()
        }
    }

}