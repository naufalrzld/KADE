package com.naufalrzld.footballmatchschedule.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.utils.toGMTFormat2
import org.jetbrains.anko.find
import java.text.SimpleDateFormat

class SearchMatchAdapter(private val events: List<MatchModel>,
                         private val listener: (MatchModel) -> Unit):
        RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_match_item, parent, false))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val date: TextView = view.find(dateNextMatch)
        private val time: TextView = view.find(timeNextMatch)
        private val homeTeam: TextView = view.find(teamHome)
        private val awayTeam: TextView = view.find(teamAway)

        @SuppressLint("SimpleDateFormat")
        fun bind(event: MatchModel, listener: (MatchModel) -> Unit) {
            var strTime = "-"
            var strDate: String? = event.dateEvent
            if (event.strTime != null) {
                val dateTime = toGMTFormat2(event.dateEvent, event.strTime)
                val datePattern = "dd/MM/yyyy"
                val timePattern = "HH:mm"

                val sdfDate = SimpleDateFormat(datePattern)
                strDate = sdfDate.format(dateTime)

                val sdfTime = SimpleDateFormat(timePattern)
                strTime = sdfTime.format(dateTime)
            }

            date.text = strDate
            time.text = strTime
            homeTeam.text = event.strHomeTeam
            awayTeam.text = event.strAwayTeam

            itemView.setOnClickListener { listener(event) }
        }
    }
}