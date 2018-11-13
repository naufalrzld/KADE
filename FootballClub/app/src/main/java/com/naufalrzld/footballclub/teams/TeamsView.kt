package com.naufalrzld.footballclub.teams

import com.naufalrzld.footballclub.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}