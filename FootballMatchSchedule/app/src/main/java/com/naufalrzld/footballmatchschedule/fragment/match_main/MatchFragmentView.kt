package com.naufalrzld.footballmatchschedule.fragment.match_main

import android.support.v4.view.ViewPager
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter

interface MatchFragmentView {
    fun setupViewPager(viewPagerAdapter: ViewPagerAdapter)
}