package com.naufalrzld.footballmatchschedule.search

import com.naufalrzld.footballmatchschedule.model.MatchModel

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showData(event: List<MatchModel>)
}