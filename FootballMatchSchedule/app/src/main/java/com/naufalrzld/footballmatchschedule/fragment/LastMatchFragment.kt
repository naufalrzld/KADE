package com.naufalrzld.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballmatchschedule.detail.DetailActivity
import com.naufalrzld.footballmatchschedule.R.id.rvListMatch
import com.naufalrzld.footballmatchschedule.R.id.swipe
import com.naufalrzld.footballmatchschedule.adapter.LastMatchAdapter
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.MatchResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService

import com.naufalrzld.footballmatchschedule.ui.MatchFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment() {

    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var events: MutableList<MatchModel> = mutableListOf()
    private var adapter: LastMatchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = MatchFragmentUI<Fragment>().createView(AnkoContext.create(context!!, this))
        swipeRefresh = view.find(swipe)
        val rvList: RecyclerView = view.find(rvListMatch)

        getMatch()

        swipeRefresh.onRefresh {
            getMatch()
        }

        rvList.layoutManager = LinearLayoutManager(context)
        adapter = LastMatchAdapter(context!!, events) {
            startActivity(intentFor<DetailActivity>("data" to it))
        }
        rvList.adapter = adapter

        return view
    }

    fun getMatch() {
        val call = RetrofitService.sendRequest()?.APILastEvents(4328)
        if (call != null) {
            call.enqueue(object: Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    try {
                        if (response.isSuccessful) {
                            swipeRefresh.isRefreshing = false
                            val eventModel = response.body()?.events
                            val count = eventModel?.size

                            for (i in 0 until count!!) {
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

                                events.add(MatchModel(strHomeTeam, intHomeScore, strAwayTeam, intAwayScore, strDate,
                                    strHomeLineupGoalkeeper, strAwayLineupGoalkeeper, strHomeLineupDefense,
                                    strAwayLineupDefense, strHomeLineupMidfield, strAwayLineupMidfield, strHomeLineupForward,
                                    strAwayLineupForward, strHomeLineupSubstitutes, strAwayLineupSubstitutes))
                            }
                            adapter?.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }
}
