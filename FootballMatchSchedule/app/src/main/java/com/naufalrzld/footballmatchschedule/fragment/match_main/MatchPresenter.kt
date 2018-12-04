package com.naufalrzld.footballmatchschedule.fragment.match_main

import android.support.v4.app.Fragment
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import com.naufalrzld.footballmatchschedule.fragment.last_match.LastMatchFragment
import com.naufalrzld.footballmatchschedule.fragment.next_match.NextMatchFragmet

class MatchPresenter(private val view : MatchFragmentView, private val fragment: Fragment) {
    fun loadViewPager() {
        val adapter = ViewPagerAdapter(fragment.childFragmentManager)
        adapter.addFragment(LastMatchFragment(), "Last")
        adapter.addFragment(NextMatchFragmet(), "Next")
        view.setupViewPager(adapter)
    }
}