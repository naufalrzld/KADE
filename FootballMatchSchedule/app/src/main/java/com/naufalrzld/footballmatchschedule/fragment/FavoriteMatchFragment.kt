package com.naufalrzld.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.FavoriteMatchAdapter
import com.naufalrzld.footballmatchschedule.adapter.LastMatchAdapter
import com.naufalrzld.footballmatchschedule.detail.DetailActivity
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.ui.MatchFragmentUI
import com.naufalrzld.footballmatchschedule.utils.database
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment() {
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var events: MutableList<MatchModel> = mutableListOf()
    private lateinit var adapter: LastMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = MatchFragmentUI<Fragment>().createView(AnkoContext.create(context!!, this))
        swipeRefresh = view.find(R.id.swipe)
        val rvList: RecyclerView = view.find(R.id.rvListMatch)

        swipeRefresh.onRefresh {
            showFavorite()
        }

        rvList.layoutManager = LinearLayoutManager(context)
        adapter = LastMatchAdapter(context!!, events) {
            startActivity(intentFor<DetailActivity>("data" to it))
        }
        rvList.adapter = adapter

        showFavorite()

        return view
    }

    private fun showFavorite(){
        events.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(MatchModel.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<MatchModel>())
            events.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}