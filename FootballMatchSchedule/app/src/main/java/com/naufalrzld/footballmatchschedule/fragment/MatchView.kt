package com.naufalrzld.footballmatchschedule.fragment

import com.naufalrzld.footballmatchschedule.model.LeagueModel
import com.naufalrzld.footballmatchschedule.model.MatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<MatchModel>)
    fun setSpinner(league: List<LeagueModel>)
}