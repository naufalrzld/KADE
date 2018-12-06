package com.naufalrzld.footballmatchschedule.fragment.next_match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.detail.match.DetailMatchActivity
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.adapter.NextMatchAdapter
import com.naufalrzld.footballmatchschedule.fragment.MatchView
import com.naufalrzld.footballmatchschedule.model.MatchModel

import com.naufalrzld.footballmatchschedule.ui.MatchFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class NextMatchFragmet : Fragment(), MatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rvList: RecyclerView
    private lateinit var spinner: Spinner

    private var adapter: NextMatchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = MatchFragmentUI<Fragment>(0).createView(AnkoContext.create(context!!, this))
        swipeRefresh = view.find(swipe)
        rvList = view.find(rvListMatch)
        spinner = view.find(R.id.spinner)

        presenter = NextMatchPresenter(this)
        presenter.getLiga(context)
        presenter.getMatch()

        swipeRefresh.onRefresh { presenter.getMatch() }

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
        adapter = NextMatchAdapter(context!!, data) {
            startActivity(intentFor<DetailMatchActivity>("data" to it))
        }

        rvList.adapter = adapter
    }

    override fun setSpinner(adapter: ArrayAdapter<String>) {
        spinner.adapter = adapter
    }
}
