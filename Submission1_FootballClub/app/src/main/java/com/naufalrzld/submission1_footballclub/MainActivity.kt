package com.naufalrzld.submission1_footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.naufalrzld.submission1_footballclub.adapter.RecyclerViewAdapter
import com.naufalrzld.submission1_footballclub.data.Item
import com.naufalrzld.submission1_footballclub.ui.MainActivityUI
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        val adapter = RecyclerViewAdapter(this, items) {
            startActivity<DetailClubActivity>("data" to it)
        }
        MainActivityUI(adapter).setContentView(this)
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val desc = resources.getStringArray(R.array.club_desc)
        val image = resources.obtainTypedArray(R.array.club_image)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i], desc[i], image.getResourceId(i, 0)))
        }

        image.recycle()
    }
}
