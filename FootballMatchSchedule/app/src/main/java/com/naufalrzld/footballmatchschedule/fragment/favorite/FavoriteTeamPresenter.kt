package com.naufalrzld.footballmatchschedule.fragment.favorite

import android.content.Context
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val view: FavoriteTeamView, val context: Context?) {
    fun loadFavorite() {
        context?.database?.use {
            val result = select(Team.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<Team>())
            view.showFavorites(favorite)
        }
    }
}