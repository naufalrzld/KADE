package com.naufalrzld.footballmatchschedule.fragment

import android.util.Log
import com.naufalrzld.footballmatchschedule.fragment.last_match.LastMatchPresenter
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.MatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LastMatchPresenterTest {

    @Mock
    private
    lateinit var view: MatchView

    private lateinit var presenter: LastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view)
    }

    @Test
    fun getMatch() {
        var events: List<MatchModel> = ArrayList<MatchModel>()
        val call = RetrofitService.sendRequest()?.APILastEvents(4328)
        if (call != null) {
            call.enqueue(object: Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    try {
                        if (response.isSuccessful) {
                            val eventModel = response.body()?.events
                            if (eventModel != null) {
                                events = eventModel
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        }

        presenter.getMatch()

        Mockito.verify(view).showLoading()
        Thread.sleep(3000)
        Mockito.verify(view).showData(events)
        Mockito.verify(view).hideLoading()
    }
}