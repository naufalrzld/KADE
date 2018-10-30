package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class MatchResponse (
    @SerializedName("events")
    val events: List<MatchModel>?
)