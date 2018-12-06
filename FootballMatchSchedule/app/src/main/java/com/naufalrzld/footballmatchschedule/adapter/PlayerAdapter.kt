package com.naufalrzld.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.model.PlayerModel
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(private val player: List<PlayerModel>,
                    private val listener: (PlayerModel) -> Unit):
        RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false))

    override fun getItemCount() = player.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(player[position], listener)
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val imgPlayer: ImageView = view.find(R.id.imgPlayer)
        private val playerName: TextView = view.find(R.id.playerName)
        private val playerPosition: TextView = view.find(R.id.playerPosition)

        fun bind(player: PlayerModel, listener: (PlayerModel) -> Unit) {
            Glide.with(view).load(player.strCutout).into(imgPlayer)
            playerName.text = player.strPlayer
            playerPosition.text = player.strPosition

            itemView.onClick { listener(player) }
        }
    }

}