package com.naufalrzld.footballmatchschedule.detail.match

import com.naufalrzld.footballmatchschedule.model.MatchModel

interface DetaiMatchlView {
    fun showLogoHome(imageUrl: String?)
    fun showLogoAway(imageUrl: String?)
    fun showData(data: MatchModel)
}