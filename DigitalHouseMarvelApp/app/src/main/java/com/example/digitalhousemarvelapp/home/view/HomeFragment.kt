package com.example.digitalhousemarvelapp.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R

class HomeFragment : Fragment() {
    lateinit var _view:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = _view.findViewById<RecyclerView>(R.id.listComics)
        val manager = GridLayoutManager(_view.context,3)


    }

    companion object {

        fun newInstance() = HomeFragment()

    }
}