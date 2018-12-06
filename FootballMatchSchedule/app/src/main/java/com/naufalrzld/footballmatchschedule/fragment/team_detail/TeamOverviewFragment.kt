package com.naufalrzld.footballmatchschedule.fragment.team_detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.naufalrzld.footballmatchschedule.R
import org.jetbrains.anko.find

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class TeamOverviewFragment(private val desc: String) : Fragment() {

    private lateinit var teamDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team_overview, container, false)
        teamDescription = view.find(R.id.teamDescription)

        teamDescription.text = desc

        return view
    }


}
