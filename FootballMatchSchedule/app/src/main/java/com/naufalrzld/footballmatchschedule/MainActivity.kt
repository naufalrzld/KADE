package com.naufalrzld.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import com.naufalrzld.footballmatchschedule.fragment.LastMatchFragment
import com.naufalrzld.footballmatchschedule.fragment.NextMatchFragmet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Football Match Schedule"

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
    }

    fun setupViewPager(viewPager : ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragmet(), "Next Match")
        viewPager.adapter = adapter
    }
}
