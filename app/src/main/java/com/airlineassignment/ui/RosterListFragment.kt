package com.airlineassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airlineassignment.R

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

        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RosterListAdapter {
            Toast.makeText(requireContext(), "${it.aircraftType}", Toast.LENGTH_SHORT).show()
        }

        RVrosterlist.adapter = adapter

        viewModel.rosterList.observe(viewLifecycleOwner, {
            it.let { adapter.addHeaderAndSubmitList(it) }
        })


        viewModel.getRosterList()
    }

}