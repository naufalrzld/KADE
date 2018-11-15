package com.naufalrzld.footballmatchschedule.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballmatchschedule.detail.DetailActivity
import com.naufalrzld.footballmatchschedule.R.id.rvListMatch
import com.naufalrzld.footballmatchschedule.R.id.swipe
import com.naufalrzld.footballmatchschedule.adapter.LastMatchAdapter
import com.naufalrzld.footballmatchschedule.model.MatchModel

import com.naufalrzld.footballmatchschedule.ui.MatchFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MatchView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rvList: RecyclerView

    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = MatchFragmentUI<Fragment>().createView(AnkoContext.create(context!!, this))
        swipeRefresh = view.find(swipe)
        rvList = view.find(rvListMatch)

        presenter = LastMatchPresenter(this)
        presenter.getMatch()

        swipeRefresh.onRefresh {
            presenter.getMatch()
        }

        rvList.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showData(data: List<MatchModel>) {
        adapter = LastMatchAdapter(context!!, data) {
            startActivity(intentFor<DetailActivity>("data" to it))
        }
        rvList.adapter = adapter
    }
}
