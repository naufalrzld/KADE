package com.naufalrzld.footballclub.detail

import com.naufalrzld.footballclub.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}