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

class TeamsUI<T>: AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>): View {
        return with(ui) {
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner {
                    id = R.id.spinner
                }
                swipeRefreshLayout {
                    id = R.id.swipe
                    setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout{
                        lparams (width = matchParent, height = wrapContent)

                        recyclerView {
                            id = R.id.list_team
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }
                    }
                }
            }
        }
    }
}