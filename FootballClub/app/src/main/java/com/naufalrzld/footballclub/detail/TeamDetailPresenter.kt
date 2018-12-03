package com.naufalrzld.footballclub.detail

import com.google.gson.Gson
import com.naufalrzld.footballclub.api.ApiRepository
import com.naufalrzld.footballclub.api.TheSportDBApi
import com.naufalrzld.footballclub.model.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data =gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java)

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}