package com.naufalrzld.footballmatchschedule.ui

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.naufalrzld.footballmatchschedule.R
import org.jetbrains.anko.*

class LastMatchItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                textView {
                    id = R.id.dateLastMatch
                    textColor = R.color.colorPrimary
                    text = "20 Desember 2018"
                }.lparams(width = wrapContent) {
                    bottomMargin = dip(8)
                }

                textView {
                    id = R.id.timeLastMatch
                    textColor = R.color.colorPrimary
                    text = "20:00"
                }.lparams(width = wrapContent) {
                    bottomMargin = dip(8)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    linearLayout {
                        lparams(width = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.score1
                            text = "2"
                            textSize = 18f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent)

                        textView {
                            id = R.id.team1
                            text = "Team 1"
                            textSize = 18f
                        }.lparams(width = wrapContent)
                    }

                    textView {
                        text = "VS"
                        textSize = 16f
                    }.lparams(width = wrapContent) {
                        leftMargin = dip(32)
                        rightMargin = dip(32)
                    }

                    linearLayout {
                        lparams(width = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.score2
                            text = "2"
                            textSize = 18f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent)

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

}