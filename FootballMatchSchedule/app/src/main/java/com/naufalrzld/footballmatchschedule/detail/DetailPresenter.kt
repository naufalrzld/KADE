package com.naufalrzld.footballmatchschedule.detail

import android.util.Log
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.TeamLogoResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView) {

    fun getEventDetail(matchModel: MatchModel?) {
        if (matchModel != null) {
            view.showData(matchModel)
        }
    }

    fun getLogoHome(teamName: String?) {
        val call = RetrofitService.sendRequest()?.APITeams(teamName!!)
        if (call != null) {
            call.enqueue(object: Callback<TeamLogoResponse> {
                override fun onFailure(call: Call<TeamLogoResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<TeamLogoResponse>, response: Response<TeamLogoResponse>) {
                    try {
                        if (response.isSuccessful) {
                            val teamLogo = response.body()?.teams?.get(0)
                            view.showLogoHome(teamLogo?.strTeamBadge)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }

    fun getLogoAway(teamName: String?) {
        val call = RetrofitService.sendRequest()?.APITeams(teamName!!)
        if (call != null) {
            call.enqueue(object: Callback<TeamLogoResponse> {
                override fun onFailure(call: Call<TeamLogoResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<TeamLogoResponse>, response: Response<TeamLogoResponse>) {
                    try {
                        if (response.isSuccessful) {
                            val teamLogo = response.body()?.teams?.get(0)
                            view.showLogoAway(teamLogo?.strTeamBadge)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }
}