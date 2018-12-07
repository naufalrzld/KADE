package com.naufalrzld.footballmatchschedule.detail.match

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.naufalrzld.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.naufalrzld.footballmatchschedule.R.id.add_to_favorite
import com.naufalrzld.footballmatchschedule.R.menu.detail_menu
import com.naufalrzld.footballmatchschedule.R.string.detail_activity
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.utils.database
import com.naufalrzld.footballmatchschedule.utils.toGMTFormat
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), DetaiMatchlView {

    private lateinit var presenter: DetailMatchPresenter

    private lateinit var match: MatchModel
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataIntent = intent
        val matchModel = dataIntent.getParcelableExtra<MatchModel>("data")
        match = matchModel
        if (matchModel.idEvent != null) {
            id = matchModel.idEvent
        } else {
            id = "0"
        }

        /*val dateFormatGmt = SimpleDateFormat("dd/MM/yy HH:mm:ss+HH:mm")

        try {
            val d = dateFormatGmt.parse(matchModel.strDate + " " + matchModel.strTime)
            dateFormatGmt.timeZone = TimeZone.getTimeZone("GMT + 7")
            val a = dateFormatGmt.format(d)
            Log.d("tanggal", a)
        } catch (ex: ParseException) {
            Log.d("Exception", ex.getLocalizedMessage())
        }*/

        favoriteState()

        presenter = DetailMatchPresenter(this)
        presenter.getEventDetail(matchModel)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLogoHome(imageUrl: String?) {
        Glide.with(baseContext).load(imageUrl).into(img_team_home)
    }

    override fun showLogoAway(imageUrl: String?) {
        Glide.with(baseContext).load(imageUrl).into(img_team_away)
    }

    @SuppressLint("SimpleDateFormat")
    override fun showData(data: MatchModel) {
        var date: String? = data.dateEvent
        if (data.strDate != null) {
            date = data.strDate
        }

        val dateTime = toGMTFormat(date, data.strTime)

        val datePattern = "dd/MM/yyyy"
        val timePattern = "HH:mm"

        val sdfDate = SimpleDateFormat(datePattern)
        val strDate = sdfDate.format(dateTime)

        val sdfTime = SimpleDateFormat(timePattern)
        val strTime = sdfTime.format(dateTime)

        val homeTeam = data.strHomeTeam
        val homeScore = data.intHomeScore
        val awayTeam = data.strAwayTeam
        val awayScore = data.intAwayScore
        val homeShot = data.intHomeShots
        val awayShot = data.intAwayShots
        val goalDetailHome = data.strHomeGoalDetails?.replace(";", "\n")
        val goalDetailAway = data.strAwayGoalDetails?.replace(";", "\n")
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

        tv_date.text = strDate
        tv_time.text = strTime
        tv_team_home.text = homeTeam
        tv_score_home.text = homeScore
        tv_team_away.text = awayTeam
        tv_score_away.text = awayScore
        tv_goal_detail_home.text = goalDetailHome
        tv_goal_detail_away.text = goalDetailAway
        tv_shot_home.text = homeShot
        tv_shot_away.text = awayShot
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

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    MatchModel.TABLE_MATCH_FAVORITE,
                    MatchModel.EVENT_ID to match.idEvent,
                    MatchModel.HOME_TEAM to match.strHomeTeam,
                    MatchModel.HOME_SCORE to match.intHomeScore,
                    MatchModel.AWAY_TEAM to match.strAwayTeam,
                    MatchModel.AWAY_SCORE to match.intAwayScore,
                    MatchModel.HOME_SHOT to match.intHomeShots,
                    MatchModel.AWAY_SHOT to match.intAwayShots,
                    MatchModel.DATE_EVENT to match.dateEvent,
                    MatchModel.EVENT_DATE to match.strDate,
                    MatchModel.EVENT_TIME to match.strTime,
                    MatchModel.HOME_GOAL_DETAIL to match.strHomeGoalDetails,
                    MatchModel.AWAY_GOAL_DETAIL to match.strAwayGoalDetails,
                    MatchModel.HOME_GOAL_KEEPER to match.strHomeLineupGoalkeeper,
                    MatchModel.AWAY_GOAL_KEEPER to match.strAwayLineupGoalkeeper,
                    MatchModel.HOME_DEFENSE to match.strHomeLineupDefense,
                    MatchModel.AWAY_DEFENSE to match.strAwayLineupDefense,
                    MatchModel.HOME_MIDFIELD to match.strHomeLineupMidfield,
                    MatchModel.AWAY_MIDFIELD to match.strAwayLineupMidfield,
                    MatchModel.HOME_SUBSTITUTES to match.strHomeLineupSubstitutes,
                    MatchModel.AWAY_SUBSTITUTES to match.strAwayLineupSubstitutes)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(MatchModel.TABLE_MATCH_FAVORITE, "(EVENT_ID = {id})",
                    "id" to id)
            }
            toast( "Removed to favorite")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(MatchModel.TABLE_MATCH_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<MatchModel>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
