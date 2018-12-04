package com.naufalrzld.footballmatchschedule.fragment.teams

import android.widget.ArrayAdapter
import com.naufalrzld.footballclub.model.Team

interface TeamsView {
    fun showTeams(teams: List<Team>)
    fun showLoading()
    fun hideLoading()
    fun setSpinner(adapter: ArrayAdapter<String>)
}