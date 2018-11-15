package com.naufalrzld.footballmatchschedule.fragment

import android.util.Log
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.MatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NextMatchPresenter(private val view : MatchView) {
    fun getMatch() {
        view.showLoading()
        val events: MutableList<MatchModel> = mutableListOf()
        val call = RetrofitService.sendRequest()?.APINextEvents(4328)
        if (call != null) {
            call.enqueue(object: Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.d("error", t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    try {
                        if (response.isSuccessful) {
                            view.hideLoading()
                            val eventModel = response.body()?.events
                            val count = eventModel?.size

                            for (i in 0 until count!!) {
                                val idEvent = eventModel.get(i).idEvent
                                val strDate = eventModel.get(i).strDate
                                val strHomeTeam = eventModel.get(i).strHomeTeam
                                val intHomeScore = eventModel.get(i).intHomeScore
                                val strAwayTeam = eventModel.get(i).strAwayTeam
                                val intAwayScore = eventModel.get(i).intAwayScore
                                val strHomeLineupGoalkeeper = eventModel.get(i).strHomeLineupGoalkeeper
                                val strAwayLineupGoalkeeper = eventModel.get(i).strAwayLineupGoalkeeper
                                val strHomeLineupDefense = eventModel.get(i).strHomeLineupDefense
                                val strAwayLineupDefense = eventModel.get(i).strAwayLineupDefense
                                val strHomeLineupMidfield = eventModel.get(i).strHomeLineupMidfield
                                val strAwayLineupMidfield = eventModel.get(i).strAwayLineupMidfield
                                val strHomeLineupForward = eventModel.get(i).strHomeLineupForward
                                val strAwayLineupForward = eventModel.get(i).strAwayLineupForward
                                val strHomeLineupSubstitutes = eventModel.get(i).strHomeLineupSubstitutes
                                val strAwayLineupSubstitutes = eventModel.get(i).strAwayLineupSubstitutes

                                events.add(MatchModel(null, idEvent, strHomeTeam, intHomeScore, strAwayTeam, intAwayScore, strDate,
                                    strHomeLineupGoalkeeper, strAwayLineupGoalkeeper, strHomeLineupDefense,
                                    strAwayLineupDefense, strHomeLineupMidfield, strAwayLineupMidfield, strHomeLineupForward,
                                    strAwayLineupForward, strHomeLineupSubstitutes, strAwayLineupSubstitutes))
                            }

                            view.showData(events)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }
}