package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamLogoResponse(
    @SerializedName("teams")
    val teams: List<TeamLogoModel>?
)