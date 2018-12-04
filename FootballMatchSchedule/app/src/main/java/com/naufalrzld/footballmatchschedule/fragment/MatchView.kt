package com.naufalrzld.footballmatchschedule.fragment

import android.widget.ArrayAdapter
import com.naufalrzld.footballmatchschedule.model.MatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<MatchModel>)
    fun setSpinner(adapter: ArrayAdapter<String>)
}