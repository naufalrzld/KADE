package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamLogoModel(
    @SerializedName("strTeamBadge")
    val strTeamBadge: String?
)