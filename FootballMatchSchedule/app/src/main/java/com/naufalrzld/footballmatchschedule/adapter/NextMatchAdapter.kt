package com.naufalrzld.footballmatchschedule.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.id.*
import com.naufalrzld.footballmatchschedule.model.MatchModel
import com.naufalrzld.footballmatchschedule.utils.toGMTFormat
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NextMatchAdapter(private val events: List<MatchModel>,
                       private val onImgClick: ImgReminderClickListener,
                       private val listener: (MatchModel) -> Unit):
        RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.next_match_item, parent, false))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], listener, onImgClick)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imgReminder: ImageView = view.find(R.id.imgReminder)
        private val date: TextView = view.find(dateNextMatch)
        private val time: TextView = view.find(timeNextMatch)
        private val homeTeam: TextView = view.find(teamHome)
        private val awayTeam: TextView = view.find(teamAway)

        @SuppressLint("SimpleDateFormat")
        fun bind(event: MatchModel, listener: (MatchModel) -> Unit, onImgClick: ImgReminderClickListener) {
            val dateTime = toGMTFormat(event.strDate, event.strTime)

            val datePattern = "dd/MM/yyyy"
            val timePattern = "HH:mm"

            val sdfDate = SimpleDateFormat(datePattern)
            val strDate = sdfDate.format(dateTime)

            val sdfTime = SimpleDateFormat(timePattern)
            val strTime = sdfTime.format(dateTime)

            date.text = strDate
            time.text = strTime
            homeTeam.text = event.strHomeTeam
            awayTeam.text = event.strAwayTeam

            imgReminder.onClick { onImgClick.onClickListener(event) }

            itemView.onClick { listener(event) }
        }
    }

    interface ImgReminderClickListener {
        fun onClickListener(match: MatchModel)
    }
}