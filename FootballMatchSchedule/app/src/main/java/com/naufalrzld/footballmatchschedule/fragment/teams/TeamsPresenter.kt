package com.naufalrzld.footballmatchschedule.fragment.teams

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballclub.model.TeamResponse
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsPresenter(private val view: TeamsView) {
    fun loadTeams(league: String) {
        view.showLoading()
        var teams: List<Team> = ArrayList()
        val call = RetrofitService.sendRequest()?.APISearchAllTeam(league)
        if (call != null) {
            call.enqueue(object: Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Log.d("error", t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                    if (response.isSuccessful) {
                        view.hideLoading()
                        val team = response.body()?.teams
                        if (team != null) {
                            teams = team
                        }

                        view.showTeams(teams)
                    }
                }
            })
        }
    }

    fun searchTeam(team: String) {
        view.showLoading()
        var teams: List<Team> = ArrayList()
        val call = RetrofitService.sendRequest()?.APISearchTeam(team)
        if (call != null) {
            call.enqueue(object: Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Log.d("error", t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        val teamModel = response.body()?.teams
                        if (teamModel != null) {
                            teams = teamModel
                        }

                        view.showTeams(teams)
                    }
                }

            })
        }
    }

    fun getLiga(context: Context) {
        val spinnerItems = context.resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        view.setSpinner(spinnerAdapter)
    }
}