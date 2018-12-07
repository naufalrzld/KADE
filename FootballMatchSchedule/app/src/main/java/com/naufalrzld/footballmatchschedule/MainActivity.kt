package com.naufalrzld.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.fragment.favorite.FavoriteMainFragment
import com.naufalrzld.footballmatchschedule.fragment.match_main.MatchFragment
import com.naufalrzld.footballmatchschedule.fragment.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var currFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            currFragment = supportFragmentManager.findFragmentById(R.id.container)
            when (item.itemId) {
                match -> {
                    if (currFragment !is MatchFragment) {
                        loadFragment(MatchFragment())
                        setToolbarTitle("Match")
                    }
                }
                teams -> {
                    if (currFragment !is TeamsFragment) {
                        loadFragment(TeamsFragment())
                        setToolbarTitle("Teams")
                    }
                }
                favorites -> {
                    if (currFragment !is FavoriteMainFragment) {
                        loadFragment(FavoriteMainFragment())
                        setToolbarTitle("Favorite")
                    }
                }
            }

            true
        }

        bottom_navigation.selectedItemId = match
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
