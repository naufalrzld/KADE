package com.naufalrzld.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.fragment.FavoriteMatchFragment
import com.naufalrzld.footballmatchschedule.fragment.LastMatchFragment
import com.naufalrzld.footballmatchschedule.fragment.NextMatchFragmet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            val currFragment = supportFragmentManager.findFragmentById(R.id.container)
            when (item.itemId) {
                last_match -> {
                    if (currFragment !is LastMatchFragment) {
                        loadFragment(LastMatchFragment())
                        setToolbarTitle("Last Match")
                    }
                }
                next_match -> {
                    if (currFragment !is NextMatchFragmet) {
                        loadFragment(NextMatchFragmet())
                        setToolbarTitle("Next Match")
                    }
                }
                favorites -> {
                    if (currFragment !is FavoriteMatchFragment) {
                        loadFragment(FavoriteMatchFragment())
                        setToolbarTitle("Favorite Match")
                    }
                }
            }

            true
        }

        bottom_navigation.selectedItemId = last_match
    }

    fun loadFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}
