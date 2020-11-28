package com.example.digitalhousemarvelapp.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.home.model.ComicModel

class HomeAdapter(
    private val dataSet: List<ComicModel>
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comics_list_item, parent, false)

        return HomeViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

}
