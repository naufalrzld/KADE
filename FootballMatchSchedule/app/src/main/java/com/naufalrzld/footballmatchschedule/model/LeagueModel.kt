package com.naufalrzld.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class LeagueModel(
    @SerializedName("idLeague")
    val idLeague: String?,

    @SerializedName("strLeague")
    val strLeague: String?
)