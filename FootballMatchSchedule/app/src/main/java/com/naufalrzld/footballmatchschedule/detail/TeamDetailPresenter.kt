package com.naufalrzld.footballmatchschedule.detail

import android.content.Intent
import com.naufalrzld.footballclub.model.Team

class TeamDetailPresenter(val view: TeamDetailView) {
    fun getData(team: Team) {
        view.setView(team)
    }
}