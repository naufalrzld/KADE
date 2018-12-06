package com.naufalrzld.footballmatchschedule.fragment.team_detail

import android.util.Log
import com.naufalrzld.footballmatchschedule.model.PlayerModel
import com.naufalrzld.footballmatchschedule.model.PlayerResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerPresenter(private val view: PlayerView) {
    fun getPlayer(team: String) {
        view.showLoading()
        val call = RetrofitService.sendRequest()?.APIPlayer(team)
        if (call != null) {
            call.enqueue(object: Callback<PlayerResponse> {
                override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                    Log.d("error", t.message)
                    view.hideLoading()
                }

                override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                    if (response.isSuccessful) {
                        view.hideLoading()
                        var listPlayer: List<PlayerModel> = ArrayList()
                        val player = response.body()?.player
                        if (player != null) {
                            listPlayer = player
                        }

                        view.showData(listPlayer)
                    }
                }
            })
        }
    }
}