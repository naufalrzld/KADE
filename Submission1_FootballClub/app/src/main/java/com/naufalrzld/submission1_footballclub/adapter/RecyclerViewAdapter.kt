package com.naufalrzld.submission1_footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.naufalrzld.submission1_footballclub.data.Item
import com.naufalrzld.submission1_footballclub.R
import kotlinx.android.synthetic.main.club_item.view.*

class RecyclerViewAdapter(private val context: Context, private val items: List<Item>, private val listener:
    (Item) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.club_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(items: Item, listener: (Item) -> Unit) {
            containerView.name.text = items.name
            Glide.with(containerView).load(items.image).into(containerView.image)
            containerView.setOnClickListener {
                listener(items)
            }
        }
    }
}