package com.naufalrzld.footballclub.detail

import com.google.gson.Gson
import com.naufalrzld.footballclub.api.ApiRepository
import com.naufalrzld.footballclub.api.TheSportDBApi
import com.naufalrzld.footballclub.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }
}