package com.naufalrzld.footballmatchschedule.service

import com.naufalrzld.footballclub.model.TeamResponse
import com.naufalrzld.footballmatchschedule.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("/api/v1/json/1/eventspastleague.php")
    fun APILastEvents(@Query("id") league: String): Call<MatchResponse>

    @GET("/api/v1/json/1/eventsnextleague.php")
    fun APINextEvents(@Query("id") league: String): Call<MatchResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/searchteams.php")
    fun APITeams(@Query("t") league: String): Call<TeamLogoResponse>

    @GET("/api/v1/json/1/search_all_teams.php")
    fun APISearchAllTeam(@Query("l") league: String): Call<TeamResponse>

    @GET("/api/v1/json/1/all_leagues.php")
    fun APIAllLeagues(): Call<LeagueResponse>

    @GET("/api/v1/json/1/searchplayers.php")
    fun APIPlayer(@Query("t") team: String): Call<PlayerResponse>

    @GET("/api/v1/json/1/searchteams.php")
    fun APISearchTeam(@Query("t") team: String): Call<TeamResponse>

    @GET("/api/v1/json/1/searchevents.php")
    fun APISearchMatch(@Query("e") event: String): Call<SearchMatchResponse>
}