package com.naufalrzld.footballmatchschedule.detail.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.model.PlayerModel
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.detail_player_activity)

        val dataIntent = intent
        val player = dataIntent.getParcelableExtra<PlayerModel>("data");

        Glide.with(this).load(player.strFanart1).into(playerImage)
        weight.text = player.strWeight
        height.text = player.strHeight
        playerPosition.text = player.strPosition
        playerDesc.text = player.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
