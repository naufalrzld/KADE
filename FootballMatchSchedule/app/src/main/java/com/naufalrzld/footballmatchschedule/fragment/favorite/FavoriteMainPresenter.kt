package com.naufalrzld.footballmatchschedule.fragment.favorite

import android.support.v4.app.Fragment
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter

class FavoriteMainPresenter(private val view: FavoriteMainView, private val fragment: Fragment) {
    fun loadViewPager() {
        val adapter = ViewPagerAdapter(fragment.childFragmentManager)
        adapter.addFragment(FavoriteMatchFragment(), "Match")
        adapter.addFragment(FavoriteTeamFragment(), "Teams")
        view.setupViewPager(adapter)
    }
}