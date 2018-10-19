package com.naufalrzld.submission1_footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naufalrzld.submission1_footballclub.data.Item
import com.naufalrzld.submission1_footballclub.ui.DetailClubUI
import org.jetbrains.anko.setContentView

class DetailClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val item = intent.getParcelableExtra<Item>("data")
        DetailClubUI(item).setContentView(this)
    }
}
