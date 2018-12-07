package com.naufalrzld.footballmatchschedule.fragment.last_match

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.fragment.MatchView
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.MatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LastMatchPresenter(private val view : MatchView) {
    fun getMatch() {
        view.showLoading()
        var events: List<MatchModel> = ArrayList()
        val call = RetrofitService.sendRequest()?.APILastEvents(4328)
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


    fun getLiga(context: Context) {
        val spinnerItems = context.resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        view.setSpinner(spinnerAdapter)
    }
}