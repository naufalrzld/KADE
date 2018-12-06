package com.naufalrzld.footballmatchschedule.fragment.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.LastMatchAdapter
import com.naufalrzld.footballmatchschedule.detail.match.DetailMatchActivity
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.ui.MatchFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var events: MutableList<MatchModel> = mutableListOf()
    private lateinit var adapter: LastMatchAdapter
    private lateinit var rvList: RecyclerView
    private lateinit var presenter: FavoriteMatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = MatchFragmentUI<Fragment>(1).createView(AnkoContext.create(ctx, this))
        swipeRefresh = view.find(R.id.swipe)
        rvList = view.find(R.id.rvListMatch)

        presenter = FavoriteMatchPresenter(this, context)

        swipeRefresh.onRefresh {
            presenter.loadFavorite()
        }

        rvList.layoutManager = LinearLayoutManager(context)

        presenter.loadFavorite()

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavorite()
    }

    override fun showFavorites(events: List<MatchModel>) {
        adapter = LastMatchAdapter(events) {
            startActivity(intentFor<DetailMatchActivity>("data" to it))
        }
        rvList.adapter = adapter
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}