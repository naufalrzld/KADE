package com.naufalrzld.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.model.TeamLogoResponse
import com.naufalrzld.footballmatchschedule.service.RetrofitService
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataIntent = intent
        val matchModel = dataIntent.getParcelableExtra<MatchModel>("data")

        if (matchModel != null) {
            val date = matchModel.strDate
            val homeTeam = matchModel.strHomeTeam
            val homeScore = matchModel.intHomeScore
            val awayTeam = matchModel.strAwayTeam
            val awayScore = matchModel.intAwayScore
            val goalKeeperHome = matchModel.strHomeLineupGoalkeeper?.replace("; ", "\n")
            val goalKeeperAway = matchModel.strAwayLineupGoalkeeper?.replace("; ", "\n")
            val defenseHome = matchModel.strHomeLineupDefense?.replace("; ", "\n")
            val defenseAway = matchModel.strAwayLineupDefense?.replace("; ", "\n")
            val forwardHome = matchModel.strHomeLineupForward?.replace("; ", "\n")
            val forwardAway = matchModel.strAwayLineupForward?.replace("; ", "\n")
            val midfieldHome = matchModel.strHomeLineupMidfield?.replace("; ", "\n")
            val midfieldAway = matchModel.strAwayLineupMidfield?.replace("; ", "\n")
            val substitutesHome = matchModel.strHomeLineupSubstitutes?.replace("; ", "\n")
            val substitutesAway = matchModel.strAwayLineupSubstitutes?.replace("; ", "\n")

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

            getLogoHome(homeTeam)
            getLogoAway(awayTeam)
        }
    }

    fun getLogoHome(teamName: String?) {
        val call = RetrofitService.sendRequest()?.APITeams(teamName!!)
        if (call != null) {
            call.enqueue(object: Callback<TeamLogoResponse> {
                override fun onFailure(call: Call<TeamLogoResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<TeamLogoResponse>, response: Response<TeamLogoResponse>) {
                    try {
                        if (response.isSuccessful) {
                            val teamLogo = response.body()?.teams?.get(0)
                            Glide.with(baseContext).load(teamLogo?.strTeamBadge).into(img_team_home)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }

    fun getLogoAway(teamName: String?) {
        val call = RetrofitService.sendRequest()?.APITeams(teamName!!)
        if (call != null) {
            call.enqueue(object: Callback<TeamLogoResponse> {
                override fun onFailure(call: Call<TeamLogoResponse>, t: Throwable) {
                    Log.d("error", t.message)
                }

                override fun onResponse(call: Call<TeamLogoResponse>, response: Response<TeamLogoResponse>) {
                    try {
                        if (response.isSuccessful) {
                            val teamLogo = response.body()?.teams?.get(0)
                            Glide.with(baseContext).load(teamLogo?.strTeamBadge).into(img_team_away)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
