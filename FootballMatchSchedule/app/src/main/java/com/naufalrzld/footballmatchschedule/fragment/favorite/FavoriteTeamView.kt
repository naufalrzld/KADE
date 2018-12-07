package com.naufalrzld.footballmatchschedule.fragment.favorite

import com.naufalrzld.footballclub.model.Team

interface FavoriteTeamView {
    fun showFavorites(team: List<Team>)
}