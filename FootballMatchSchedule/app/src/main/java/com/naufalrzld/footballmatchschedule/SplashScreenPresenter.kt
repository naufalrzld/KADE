package com.naufalrzld.footballmatchschedule

import android.content.Context
import android.util.Log
import com.naufalrzld.footballmatchschedule.model.LeagueResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import com.naufalrzld.footballmatchschedule.utils.Sharedpreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenPresenter(private val context: Context) {
    fun getLeagues() {
        val sharedPreference = Sharedpreference(context)
        val call = RetrofitService.sendRequest()?.APIAllLeagues()
        if (call != null) {
            call.enqueue(object: Callback<LeagueResponse> {
                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            sharedPreference.storeData("league", data)
                        }
                    }
                }
            })
        }
    }
}