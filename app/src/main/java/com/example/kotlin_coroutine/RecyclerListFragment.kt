package com.example.kotlin_coroutine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_coroutine.adapter.RecyclerViewAdapter
import com.example.kotlin_coroutine.model.RecyclerList
import com.example.kotlin_coroutine.view_model.MainActivityViewModel

class RecyclerListFragment : Fragment() {
    private lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initViewModel(view)
        getAllData()

        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    private fun getAllData() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //TODO: WATCH THIS CODE (WARNINGG)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getAllData()
    }


    companion object {
        @JvmStatic
        fun newInstance() = RecyclerListFragment()
    }
}