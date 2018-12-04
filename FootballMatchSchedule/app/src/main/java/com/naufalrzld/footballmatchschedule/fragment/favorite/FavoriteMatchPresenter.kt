package com.naufalrzld.footballmatchschedule.fragment.favorite

import android.content.Context
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter(private val view: FavoriteMatchView, private val context: Context?) {
    fun loadFavorite() {
        context?.database?.use {
            view.hideLoading()
            val result = select(MatchModel.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<MatchModel>())
            view.showFavorites(favorite)
        }
    }
}