package com.naufalrzld.footballclub.teams

import com.google.gson.Gson
import com.naufalrzld.footballclub.api.ApiRepository
import com.naufalrzld.footballclub.api.TheSportDBApi
import com.naufalrzld.footballclub.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {
    fun getTeamList(league: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java)

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }
}