package com.naufalrzld.footballclub.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballclub.R.color.colorAccent
import com.naufalrzld.footballclub.adapter.FavoriteTeamsAdapter
import com.naufalrzld.footballclub.detail.TeamDetailActivity
import com.naufalrzld.footballclub.model.Favorite
import com.naufalrzld.footballclub.util.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamsFragment : Fragment(), AnkoComponent<Context> {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamsAdapter(favorites){
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }

        listEvent.adapter = adapter

        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                listEvent = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}