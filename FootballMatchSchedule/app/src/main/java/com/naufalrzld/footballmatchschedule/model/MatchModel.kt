package com.naufalrzld.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchModel constructor (
    @SerializedName("strHomeTeam")
    val strHomeTeam: String?,

    @SerializedName("intHomeScore")
    val intHomeScore: String?,

    @SerializedName("strAwayTeam")
    val strAwayTeam: String?,

    @SerializedName("intAwayScore")
    val intAwayScore: String?,

    @SerializedName("strDate")
    val strDate: String?,

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
) : Parcelable