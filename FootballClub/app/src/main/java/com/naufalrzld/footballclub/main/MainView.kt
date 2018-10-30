package com.naufalrzld.footballclub.main

import com.naufalrzld.footballclub.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}