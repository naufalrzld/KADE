package com.naufalrzld.footballmatchschedule.detail.team

import android.support.v4.app.FragmentManager
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import com.naufalrzld.footballmatchschedule.fragment.team_detail.PlayerFragment
import com.naufalrzld.footballmatchschedule.fragment.team_detail.TeamOverviewFragment

class TeamDetailPresenter(val view: TeamDetailView, private val fragmentManager: FragmentManager) {
    fun getData(team: Team) {
        view.setView(team)
    }

    fun loadViewPager(data: String?, teamName: String?) {
        var desc = "-"
        var team = "-"
        if (data != null) {
            desc = data
        }
        if (teamName != null) {
            team = teamName
        }
        val adapter = ViewPagerAdapter(fragmentManager)
        adapter.addFragment(TeamOverviewFragment(desc), "Overview")
        adapter.addFragment(PlayerFragment(team), "Players")
        view.setupViewPager(adapter)
    }
}