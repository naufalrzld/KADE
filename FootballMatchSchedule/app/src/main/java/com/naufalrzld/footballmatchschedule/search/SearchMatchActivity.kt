package com.naufalrzld.footballmatchschedule.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.NextMatchAdapter
import com.naufalrzld.footballmatchschedule.detail.match.DetailMatchActivity
import com.naufalrzld.footballmatchschedule.model.MatchModel
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.intentFor
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.SearchView


class SearchMatchActivity : AppCompatActivity(), SearchMatchView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: NextMatchAdapter

    private lateinit var event: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.search_match_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = SearchMatchPresenter(this)

        rvListMatch.layoutManager = LinearLayoutManager(this)

    }

    override fun onRefresh() {
        presenter.searchMatch(event)
    }

    override fun showLoading() {
        swipe.isRefreshing = true
    }

    override fun hideLoading() {
        swipe.isRefreshing = false
    }

    override fun showData(event: List<MatchModel>) {
        adapter = NextMatchAdapter(this, event) {
            startActivity(intentFor<DetailMatchActivity>("data" to it))
        }

        rvListMatch.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.search)) as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.searchMatch(newText)
                event = newText
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

