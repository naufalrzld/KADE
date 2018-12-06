package com.naufalrzld.footballmatchschedule.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.R
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val dataIntent = intent
        val team = dataIntent.getParcelableExtra<Team>("data")

        presenter = TeamDetailPresenter(this)
        presenter.getData(team)
    }

    override fun setView(team: Team) {
        Glide.with(this).load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName
        teamFormedYear.text = team.teamFormedYear
        teamStadium.text = team.teamStadium
        teamDescription.text = team.teamDescription
    }
}
