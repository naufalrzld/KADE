package com.naufalrzld.footballmatchschedule.utils

import com.google.gson.Gson
import android.content.Context





class Sharedpreference(private val context: Context) {
    private val PREF_NAME = "Session"
    private val gson = Gson()

    val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    fun storeData(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    fun checkIfDataExists(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun <T> getObjectData(key: String, `object`: Class<T>): T {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, `object`)
    }

    fun clearAllData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}