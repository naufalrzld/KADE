package com.naufalrzld.footballmatchschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.ui.NextMatchItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class NextMatchAdapter(private val context: Context, private val events: List<MatchModel>,
                       private val listener: (MatchModel) -> Unit):
        RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(NextMatchItemUI().createView(AnkoContext.create(context, parent)))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val date: TextView = view.find(dateNextMatch)
        private val homeTeam: TextView = view.find(team1)
        private val awayTeam: TextView = view.find(team2)

        fun bind(event: MatchModel, listener: (MatchModel) -> Unit) {
            date.text = event.strDate
            homeTeam.text = event.strHomeTeam
            awayTeam.text = event.strAwayTeam

            itemView.setOnClickListener { listener(event) }
        }
    }
}