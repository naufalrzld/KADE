package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val events: List<MatchModel>?
)