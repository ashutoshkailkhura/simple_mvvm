package com.airlineassignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airlineassignment.R
import com.airlineassignment.data.local.asDomainObj
import kotlinx.coroutines.*

class RosterListFragment : Fragment() {

    private val viewModel: RosterListViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this, RosterListViewModel.Factory(activity.application)
        )[RosterListViewModel::class.java]
    }


    private lateinit var adapter: RosterListAdapter
    private lateinit var RVrosterlist: RecyclerView
    private lateinit var RefreshView: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_roster_list, container, false)

        RVrosterlist = view.findViewById(R.id.rv_roster_list)
        RefreshView = view.findViewById(R.id.refresh_roster_list)

        RVrosterlist.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RosterListAdapter {
            findNavController().navigate(
                RosterListFragmentDirections.actionRosterListFragmentToDetailFragment(
                    it.asDomainObj()
                )
            )
        }

        RVrosterlist.adapter = adapter

        RefreshView.setOnRefreshListener {
            Log.i("XXX", "onRefresh called from SwipeRefreshLayout")
            RefreshView.isRefreshing = true
            myTesting()
            adapter.submitList(listOf())
        }
        viewModel.rosterList.observe(viewLifecycleOwner, {
            it.let { adapter.addHeaderAndSubmitList(it) }
        })


        viewModel.getRosterList()
    }

    private fun myTesting() {
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.getRosterList()
            withContext(Dispatchers.Main) {
                RefreshView.isRefreshing = false
            }
        }
    }

}