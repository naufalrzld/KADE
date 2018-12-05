package com.naufalrzld.footballmatchschedule.utils

import com.google.gson.Gson
import android.content.Context

class Sharedpreference(private val context: Context) {
    private val LEAGUE = "league"
    private val sharedPreferences = context.getSharedPreferences("league_pref", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    fun <T> setLeague(value: Any) {
        val gson = Gson()
        val json = gson.toJson(value)

        editor.putString(LEAGUE, json)
        editor.commit()
    }

    fun <T> getLeague(`object`: Class<T>): T {
        val json = sharedPreferences.getString(LEAGUE, null)
        return Gson().fromJson(json, `object`)
    }
}