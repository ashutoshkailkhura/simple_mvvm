package com.airlineassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airlineassignment.R
import com.airlineassignment.data.local.RosterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class RosterListAdapter(private val onRosterClick: (RosterEntity) -> Unit) :
    ListAdapter<RosterListAdapter.RosterDataItem, RecyclerView.ViewHolder>(Companion) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<RosterEntity>) {
        adapterScope.launch {
            val items = list.map {
                RosterDataItem.RosterItem(it)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    companion object : DiffUtil.ItemCallback<RosterDataItem>() {
        override fun areItemsTheSame(
            oldItem: RosterDataItem,
            newItem: RosterDataItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RosterDataItem,
            newItem: RosterDataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    sealed class RosterDataItem {

        data class RosterItem(val roster: RosterEntity) : RosterDataItem() {
            override val id: Long = roster.id!!
        }

        data class Header(val date: String) : RosterDataItem() {
            override val id = Long.MIN_VALUE
        }

        abstract val id: Long
    }

    class HeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(date: RosterDataItem.Header) {
            view.findViewById<TextView>(R.id.tv_roster_header).text = date.date
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_roster_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(onRosterClick: (RosterEntity) -> Unit, roster: RosterDataItem.RosterItem) {
            view.findViewById<TextView>(R.id.tv_airline_route).text =
                "${roster.roster.departure} - ${roster.roster.destination}"
            view.findViewById<TextView>(R.id.tv_airline_time).text =
                "${roster.roster.timeDepart} - ${roster.roster.timeArrive}"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_roster, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val rosterItem = getItem(position) as RosterDataItem.RosterItem
                holder.bind(onRosterClick, rosterItem)
            }
            is HeaderViewHolder -> {
                val headerItem = getItem(position) as RosterDataItem.Header
                holder.bind(headerItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RosterDataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is RosterDataItem.RosterItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

}