package com.naufalrzld.footballmatchschedule.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.naufalrzld.footballmatchschedule.R
import org.jetbrains.anko.*

class NextMatchItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                textView {
                    id = R.id.dateNextMatch
                    textColor = R.color.colorPrimary
                    text = "20 Desember 2018"
                }.lparams(width = wrapContent) {
                    bottomMargin = dip(8)
                }

                textView {
                    id = R.id.timeNextMatch
                    textColor = R.color.colorPrimary
                    text = "20:00"
                }.lparams(width = wrapContent) {
                    bottomMargin = dip(8)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        id = R.id.team1
                        text = "Team 1"
                        textSize = 18f
                    }.lparams(width = wrapContent)

                    textView {
                        text = "VS"
                        textSize = 16f
                    }.lparams(width = wrapContent) {
                        leftMargin = dip(32)
                        rightMargin = dip(32)
                    }

                    textView {
                        id = R.id.team2
                        text = "Team 2"
                        textSize = 18f
                    }.lparams(width = wrapContent)
                }
            }
        }
    }

}