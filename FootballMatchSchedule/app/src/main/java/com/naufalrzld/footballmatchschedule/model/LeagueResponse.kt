package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues")
    val leagues: List<LeagueModel>?
)