package com.naufalrzld.footballmatchschedule.fragment.match_main


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*

import com.naufalrzld.footballmatchschedule.R
import com.naufalrzld.footballmatchschedule.R.id.search
import com.naufalrzld.footballmatchschedule.adapter.ViewPagerAdapter
import com.naufalrzld.footballmatchschedule.search.SearchMatchActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment(), MatchFragmentView {

    private lateinit var presenter: MatchPresenter
    private lateinit var tabs : TabLayout
    private lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_event_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            search -> startActivity(intentFor<SearchMatchActivity>())
        }
        return super.onOptionsItemSelected(item)
    }
}
