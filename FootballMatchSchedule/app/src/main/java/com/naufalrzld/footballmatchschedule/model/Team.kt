package com.naufalrzld.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Long?,

    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
) : Parcelable {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESC: String = "TEAM_DESC"
    }
}