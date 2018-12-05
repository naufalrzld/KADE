package com.naufalrzld.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.ui.LastMatchItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class LastMatchAdapter(private val events: List<MatchModel>,
                       private val listener: (MatchModel) -> Unit):
        RecyclerView.Adapter<LastMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LastMatchItemUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: LastMatchAdapter.ViewHolder, position: Int) {
        holder.bind(events[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val date: TextView = view.find(dateLastMatch)
        private val time: TextView = view.find(timeLastMatch)
        private val homeScore: TextView = view.find(score1)
        private val homeTeam: TextView = view.find(team1)
        private val awayScore: TextView = view.find(score2)
        private val awayTeam: TextView = view.find(team2)

        fun bind(event: MatchModel, listener: (MatchModel) -> Unit) {
            date.text = event.strDate
            time.text = event.strTime
            homeScore.text = event.intHomeScore
            homeTeam.text = event.strHomeTeam
            awayScore.text = event.intAwayScore
            awayTeam.text = event.strAwayTeam

            itemView.setOnClickListener { listener(event) }
        }
    }
}