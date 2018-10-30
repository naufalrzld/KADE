package com.naufalrzld.footballmatchschedule.service

import com.naufalrzld.footballmatchschedule.model.MatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("/api/v1/json/1/eventspastleague.php")
    fun APILastEvents(@Query("id") league: Int): Call<MatchResponse>
}