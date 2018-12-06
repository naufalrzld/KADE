package com.naufalrzld.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(
    @SerializedName("strPlayer")
    val strPlayer: String?,

    @SerializedName("strPosition")
    val strPosition: String?,

    @SerializedName("strWeight")
    val strWeight: String?,

    @SerializedName("strHeight")
    val strHeight: String?,

    @SerializedName("strCutout")
    val strCutout: String?,

    @SerializedName("strFanart1")
    val strFanart1: String?,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?
) : Parcelable