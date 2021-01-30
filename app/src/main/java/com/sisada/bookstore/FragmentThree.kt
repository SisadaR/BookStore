package com.sisada.bookstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class FragmentThree : Fragment() {

    private  var fictionList : MutableList<Int> = mutableListOf(
        R.drawable.fiction_one,R.drawable.fiction_six,
        R.drawable.fiction_two,R.drawable.fiction_seven,
        R.drawable.fiction_three,R.drawable.fiction_eight,
        R.drawable.fiction_four,R.drawable.fiction_nine,
        R.drawable.fiction_five,R.drawable.fiction_ten,
        R.drawable.fiction_one,R.drawable.fiction_six,
        R.drawable.fiction_two,R.drawable.fiction_seven,
        R.drawable.fiction_three,R.drawable.fiction_eight,
        R.drawable.fiction_four,R.drawable.fiction_nine,
        R.drawable.fiction_five,R.drawable.fiction_ten,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_three, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val layout = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layout
        recyclerView.adapter = RecyclerViewAdapter(fictionList)
        return view
    }


}