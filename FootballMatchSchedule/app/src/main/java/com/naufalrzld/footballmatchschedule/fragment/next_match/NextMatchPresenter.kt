package com.naufalrzld.footballmatchschedule.fragment.next_match

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.MatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import com.naufalrzld.footballmatchschedule.R.array.league
import com.naufalrzld.footballmatchschedule.adapter.SpinnerAdapter
import com.naufalrzld.footballmatchschedule.fragment.MatchView
import com.naufalrzld.footballmatchschedule.model.LeagueModel
import com.naufalrzld.footballmatchschedule.model.LeagueResponse
import com.naufalrzld.footballmatchschedule.utils.Sharedpreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.math.log

class NextMatchPresenter(private val view : MatchView) {
    fun getMatch(id: String) {
        view.showLoading()
        var events: List<MatchModel> = ArrayList()
        val call = RetrofitService.sendRequest()?.APINextEvents(id)
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
                            if (eventModel != null) {
                                events = eventModel
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

    fun getLiga(context: Context?) {
        val sharedPreference = context?.let { Sharedpreference(it) }
        val listLeague: MutableList<LeagueModel> = mutableListOf()
        if (sharedPreference?.checkIfDataExists("league")!!) {
            val data = sharedPreference?.getObjectData("league", LeagueResponse::class.java)
            for (i in 0..6) {
                data.leagues?.get(i)?.let { listLeague.add(i, it) }
            }

            view.setSpinner(listLeague)
        }
        /*if (context != null) {
            val spinnerItems = context.resources.getStringArray(league)
            val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
            view.setSpinner(spinnerAdapter)
        }*/
    }
}