package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class MatchModel constructor (
    @SerializedName("strHomeTeam")
    val strHomeTeam: String?,

    @SerializedName("intHomeScore")
    val intHomeScore: String?,

    @SerializedName("strAwayTeam")
    val strAwayTeam: String?,

    @SerializedName("intAwayScore")
    val intAwayScore: String?,

    @SerializedName("dateEvent")
    val strDate: String? = null,

    @SerializedName("idHomeTeam")
    val idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String? = null
)