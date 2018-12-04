package com.naufalrzld.footballmatchschedule.fragment.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.TeamsAdapter

import com.naufalrzld.footballmatchschedule.ui.TeamsUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsFragment : Fragment(), TeamsView {

    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvTeams: RecyclerView

    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter: TeamsPresenter
    private lateinit var leagueName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = TeamsUI<Fragment>().createView(AnkoContext.create(ctx, this))
        spinner = view.find(R.id.spinner)
        swipeRefreshLayout = view.find(R.id.swipe)
        rvTeams = view.find(R.id.list_team)

        presenter = TeamsPresenter(this)
        presenter.getLiga(ctx)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.loadTeams(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.loadTeams(leagueName)
        }

        return view
    }

    override fun showTeams(teams: List<Team>) {
        adapter = TeamsAdapter(teams) {
            toast(it.teamName!!)
        }
        rvTeams.adapter = adapter
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun setSpinner(adapter: ArrayAdapter<String>) {
        spinner.adapter = adapter
    }
}
