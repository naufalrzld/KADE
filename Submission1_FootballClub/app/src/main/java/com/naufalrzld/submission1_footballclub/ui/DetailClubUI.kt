package com.naufalrzld.submission1_footballclub.ui

import android.view.Gravity
import com.bumptech.glide.Glide
import com.naufalrzld.submission1_footballclub.DetailClubActivity
import com.naufalrzld.submission1_footballclub.data.Item
import com.naufalrzld.submission1_footballclub.R
import org.jetbrains.anko.*

class DetailClubUI(val item: Item) : AnkoComponent<DetailClubActivity> {
    override fun createView(ui: AnkoContext<DetailClubActivity>) = with(ui) {
        verticalLayout {
            padding = dip(16)

            imageView {
                id = R.id.image
                Glide.with(context).load(item.image).into(this)
            }.lparams(width = dip(100), height = dip(100)) {
                gravity = Gravity.CENTER_HORIZONTAL
            }

            textView(item.name) {
                textSize = 14f
            }.lparams(width = wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(5)
            }

            textView(item.desc) {
                id = R.id.desc
            }.lparams(width = wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(5)
            }
        }
    }
}