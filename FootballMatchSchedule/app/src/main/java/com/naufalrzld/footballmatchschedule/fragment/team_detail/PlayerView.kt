package com.naufalrzld.footballmatchschedule.fragment.team_detail

import com.naufalrzld.footballmatchschedule.model.PlayerModel

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<PlayerModel>)
}