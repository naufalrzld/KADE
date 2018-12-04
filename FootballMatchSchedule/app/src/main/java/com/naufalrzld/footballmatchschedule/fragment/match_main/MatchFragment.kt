package com.naufalrzld.footballmatchschedule.fragment.match_main


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import org.jetbrains.anko.find

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment(), MatchFragmentView {

    private lateinit var presenter: MatchPresenter
    private lateinit var tabs : TabLayout
    private lateinit var viewPager : ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        tabs = view.find(R.id.tabs)
        viewPager = view.find(R.id.viewpager)

        presenter = MatchPresenter(this, this)
        presenter.loadViewPager()

        tabs.setupWithViewPager(viewPager)

        return view
    }

    override fun setupViewPager(viewPagerAdapter: ViewPagerAdapter) {
        viewPager.adapter = viewPagerAdapter
    }
}
