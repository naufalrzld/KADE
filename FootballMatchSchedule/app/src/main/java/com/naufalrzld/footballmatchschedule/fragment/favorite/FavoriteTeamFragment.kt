package com.naufalrzld.footballmatchschedule.fragment.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalrzld.footballclub.model.Team

import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.TeamsAdapter
import com.naufalrzld.footballmatchschedule.detail.team.TeamDetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    private lateinit var rvTeam: RecyclerView

    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)

        rvTeam = view.find(R.id.rvTeam)
        rvTeam.layoutManager = LinearLayoutManager(context)

        presenter = FavoriteTeamPresenter(this, context)
        presenter.loadFavorite()

        return view
    }

    override fun showFavorites(team: List<Team>) {
        adapter = TeamsAdapter(team) {
            startActivity(intentFor<TeamDetailActivity>("data" to it))
        }

        rvTeam.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavorite()
    }
}
