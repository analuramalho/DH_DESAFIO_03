package com.example.digitalhousemarvelapp.comic.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.model.ComicModel

class ComicAdapter(
    private val dataSet: List<ComicModel>,
    private val clickListener: (ComicModel) -> Unit
) : RecyclerView.Adapter<ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comics_list_item, parent, false)

        return ComicViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val dsPosition = dataSet[position]
        holder.bind(dsPosition)
        holder.itemView.setOnClickListener{clickListener(dsPosition)}
    }

}
