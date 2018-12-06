package com.naufalrzld.footballmatchschedule.fragment.team_detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.PlayerAdapter
import com.naufalrzld.footballmatchschedule.detail.player.DetailPlayerActivity
import com.naufalrzld.footballmatchschedule.model.PlayerModel
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class PlayerFragment(private val team: String) : Fragment(), PlayerView {

    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var rvPlayer: RecyclerView

    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        swipe = view.find(R.id.swipe)
        rvPlayer = view.find(R.id.rvPlayer)

        presenter = PlayerPresenter(this)
        presenter.getPlayer(team)

        rvPlayer.layoutManager = LinearLayoutManager(context)

        swipe.onRefresh { presenter.getPlayer(team) }

        return view
    }

    override fun showLoading() {
        swipe.isRefreshing = true
    }

    override fun hideLoading() {
        swipe.isRefreshing = false
    }

    override fun showData(data: List<PlayerModel>) {
        adapter = PlayerAdapter(data) {
            startActivity(intentFor<DetailPlayerActivity>("data" to it))
        }

        rvPlayer.adapter = adapter
    }
}
