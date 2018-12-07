package com.naufalrzld.footballmatchschedule.search

import android.util.Log
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.SearchMatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchPresenter(private val view: SearchMatchView) {
    fun searchMatch(event: String) {
        view.showLoading()
        var events: List<MatchModel> = ArrayList()
        val call = RetrofitService.sendRequest()?.APISearchMatch(event)
        if (call != null) {
            call.enqueue(object: Callback<SearchMatchResponse> {
                override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                    Log.d("error", t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<SearchMatchResponse>, response: Response<SearchMatchResponse>) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        val eventModel = response.body()?.events
                        if (eventModel != null) {
                            events = eventModel
                        }

                        view.showData(events)
                    }
                }

            })
        }
    }
}