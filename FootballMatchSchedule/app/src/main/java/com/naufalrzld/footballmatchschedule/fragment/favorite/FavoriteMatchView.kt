package com.naufalrzld.footballmatchschedule.fragment.favorite

import com.naufalrzld.footballmatchschedule.model.MatchModel

interface FavoriteMatchView {
    fun showFavorites(events: List<MatchModel>)
    fun showLoading()
    fun hideLoading()
}