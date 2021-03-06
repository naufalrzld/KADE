package com.naufalrzld.footballmatchschedule.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragmentUI<T>(private val viewType: Int) : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>): View {
        return with(ui) {
            linearLayout {
                lparams (width = matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                if (viewType == 0) {
                    spinner {
                        id = R.id.spinner
                    }.lparams(width = matchParent, height = wrapContent)
                }

                swipeRefreshLayout {
                    id = R.id.swipe
                    setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        id = R.id.rvListMatch
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        }
    }

}