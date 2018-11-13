package com.naufalrzld.footballmatchschedule.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.string.detail_activity
import com.naufalrzld.footballmatchschedule.model.MatchModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataIntent = intent
        val matchModel = dataIntent.getParcelableExtra<MatchModel>("data")

        presenter = DetailPresenter(this)
        presenter.getEventDetail(matchModel)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLogoHome(imageUrl: String?) {
        Glide.with(baseContext).load(imageUrl).into(img_team_home)
    }

    override fun showLogoAway(imageUrl: String?) {
        Glide.with(baseContext).load(imageUrl).into(img_team_away)
    }

    override fun showData(data: MatchModel) {
        val date = data.strDate
        val homeTeam = data.strHomeTeam
        val homeScore = data.intHomeScore
        val awayTeam = data.strAwayTeam
        val awayScore = data.intAwayScore
        val goalKeeperHome = data.strHomeLineupGoalkeeper?.replace("; ", "\n")
        val goalKeeperAway = data.strAwayLineupGoalkeeper?.replace("; ", "\n")
        val defenseHome = data.strHomeLineupDefense?.replace("; ", "\n")
        val defenseAway = data.strAwayLineupDefense?.replace("; ", "\n")
        val forwardHome = data.strHomeLineupForward?.replace("; ", "\n")
        val forwardAway = data.strAwayLineupForward?.replace("; ", "\n")
        val midfieldHome = data.strHomeLineupMidfield?.replace("; ", "\n")
        val midfieldAway = data.strAwayLineupMidfield?.replace("; ", "\n")
        val substitutesHome = data.strHomeLineupSubstitutes?.replace("; ", "\n")
        val substitutesAway = data.strAwayLineupSubstitutes?.replace("; ", "\n")

        tv_date.text = date
        tv_team_home.text = homeTeam
        tv_score_home.text = homeScore
        tv_team_away.text = awayTeam
        tv_score_away.text = awayScore
        tv_goal_keeper_home.text = goalKeeperHome
        tv_goal_keeper_away.text = goalKeeperAway
        tv_defense_home.text = defenseHome
        tv_defense_away.text = defenseAway
        tv_forward_home.text = forwardHome
        tv_forward_away.text = forwardAway
        tv_midfield_home.text = midfieldHome
        tv_midfield_away.text = midfieldAway
        tv_substitutes_home.text = substitutesHome
        tv_substitutes_away.text = substitutesAway

        presenter.getLogoHome(homeTeam)
        presenter.getLogoAway(awayTeam)
    }
}
