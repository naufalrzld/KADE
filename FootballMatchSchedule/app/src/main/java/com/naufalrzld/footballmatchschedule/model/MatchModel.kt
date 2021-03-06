package com.naufalrzld.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchModel(
    val id: Long?,

    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("strHomeTeam")
    val strHomeTeam: String?,

    @SerializedName("intHomeScore")
    val intHomeScore: String?,

    @SerializedName("strAwayTeam")
    val strAwayTeam: String?,

    @SerializedName("intAwayScore")
    val intAwayScore: String?,

    @SerializedName("intHomeShots")
    val intHomeShots: String?,

    @SerializedName("intAwayShots")
    val intAwayShots: String?,

    @SerializedName("dateEvent")
    val dateEvent: String?,

    @SerializedName("strDate")
    val strDate: String?,

    @SerializedName("strTime")
    val strTime: String?,

    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String?,

    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    val strHomeLineupDefense: String?,

    @SerializedName("strAwayLineupDefense")
    val strAwayLineupDefense: String?,

    @SerializedName("strHomeLineupMidfield")
    val strHomeLineupMidfield: String?,

    @SerializedName("strAwayLineupMidfield")
    val strAwayLineupMidfield: String?,

    @SerializedName("strHomeLineupForward")
    val strHomeLineupForward: String?,

    @SerializedName("strAwayLineupForward")
    val strAwayLineupForward: String?,

    @SerializedName("strHomeLineupSubstitutes")
    val strHomeLineupSubstitutes: String?,

    @SerializedName("strAwayLineupSubstitutes")
    val strAwayLineupSubstitutes: String?
) : Parcelable {
    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SHOT: String = "HOME_SHOT"
        const val AWAY_SHOT: String = "AWAY_SHOT"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"
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