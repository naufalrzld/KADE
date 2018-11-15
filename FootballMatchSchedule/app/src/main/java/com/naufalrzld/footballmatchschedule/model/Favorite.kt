package com.naufalrzld.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(val id: Long?,
                    val idEvent: String?,
                    val strHomeTeam: String?,
                    val intHomeScore: String?,
                    val strAwayTeam: String?,
                    val intAwayScore: String?,
                    val strDate: String?,
                    val strHomeLineupGoalkeeper: String?,
                    val strAwayLineupGoalkeeper: String?,
                    val strHomeLineupDefense: String?,
                    val strAwayLineupDefense: String?,
                    val strHomeLineupMidfield: String?,
                    val strAwayLineupMidfield: String?,
                    val strHomeLineupForward: String?,
                    val strAwayLineupForward: String?,
                    val strHomeLineupSubstitutes: String?,
                    val strAwayLineupSubstitutes: String?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
        const val HOME_SUBSTITUTES: String = "HOME_SUBSTITUTES"
        const val AWAY_SUBSTITUTES: String = "AWAY_SUBSTITUTES"
    }
}