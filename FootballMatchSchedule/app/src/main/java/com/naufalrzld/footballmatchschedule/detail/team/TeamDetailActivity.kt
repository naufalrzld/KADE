package com.naufalrzld.footballmatchschedule.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.naufalrzld.footballclub.model.Team
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.naufalrzld.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.naufalrzld.footballmatchschedule.R.id.add_to_favorite
import com.naufalrzld.footballmatchschedule.R.menu.detail_menu
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import com.naufalrzld.footballmatchschedule.utils.database
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var team: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String? = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.detail_team_activity)

        val dataIntent = intent
        team = dataIntent.getParcelableExtra("data")

        if (team.teamId != null) {
            id = team.teamId
        }

        favoriteState()

        viewPager = find(R.id.viewpager)
        tabs = find(R.id.tabs)

        presenter = TeamDetailPresenter(this, supportFragmentManager)
        presenter.getData(team)
        presenter.loadViewPager(team.teamDescription, team.teamName)

        tabs.setupWithViewPager(viewPager)
    }

    override fun setView(team: Team) {
        Glide.with(this).load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName
        teamFormedYear.text = team.teamFormedYear
        teamStadium.text = team.teamStadium
    }

    override fun setupViewPager(viewPagerAdapter: ViewPagerAdapter) {
        viewPager.adapter = viewPagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
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
                    Team.TABLE_TEAM_FAVORITE,
                    Team.TEAM_ID to team.teamId,
                    Team.TEAM_NAME to team.teamName,
                    Team.TEAM_BADGE to team.teamBadge,
                    Team.TEAM_FORMED_YEAR to team.teamFormedYear,
                    Team.TEAM_STADIUM to team.teamStadium,
                    Team.TEAM_DESC to team.teamDescription
                )
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Team.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})",
                    "id" to id!!)
            }
            toast( "Removed to favorite")
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Team.TABLE_TEAM_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id!!)
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
