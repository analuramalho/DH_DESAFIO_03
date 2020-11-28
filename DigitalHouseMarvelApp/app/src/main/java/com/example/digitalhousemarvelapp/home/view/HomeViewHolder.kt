package com.example.digitalhousemarvelapp.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.home.model.ComicModel
import kotlinx.android.synthetic.main.fragment_comics_list_item.view.*

class HomeViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

    private val imageComic = view.findViewById<ImageView>(R.id.imageComic_Home)
    private val textComic = view.findViewById<TextView>(R.id.textComic_Home)

    fun bind(comicModel: ComicModel){

    }

}
