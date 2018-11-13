package com.naufalrzld.footballmatchschedule.detail

import com.naufalrzld.footballmatchschedule.model.MatchModel

interface DetailView {
    fun showLogoHome(imageUrl: String?)
    fun showLogoAway(imageUrl: String?)
    fun showData(data: MatchModel)
}