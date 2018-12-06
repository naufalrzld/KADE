package com.naufalrzld.footballmatchschedule.detail.team

import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter

interface TeamDetailView {
    fun setView(team: Team)
    fun setupViewPager(viewPagerAdapter: ViewPagerAdapter)
}