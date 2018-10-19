package com.naufalrzld.submission1_footballclub.ui

import android.support.v7.widget.LinearLayoutManager
import com.naufalrzld.submission1_footballclub.MainActivity
import com.naufalrzld.submission1_footballclub.adapter.RecyclerViewAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI (val mAdapter: RecyclerViewAdapter) : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            lparams(matchParent, matchParent)
            padding = dip(16)

            recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(ctx)
                adapter = mAdapter
            }
        }
    }
}