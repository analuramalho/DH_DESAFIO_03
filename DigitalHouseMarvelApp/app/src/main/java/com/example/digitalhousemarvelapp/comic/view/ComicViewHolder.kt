package com.example.digitalhousemarvelapp.comic.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.model.ComicModel
import com.squareup.picasso.Picasso

class ComicViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

    private val imageComic = view.findViewById<ImageView>(R.id.imageComic_Home)
    private val textComic = view.findViewById<TextView>(R.id.textComic_Home)

    fun bind(comicModel: ComicModel){
        textComic.text = "${comicModel.issueNumber}#"

        if (comicModel.thumbnail == null || comicModel.thumbnail.path.contains("image_not_available")) {
            Picasso.get()
                .load(R.drawable.comic)
                .into(imageComic)
        } else {
            val imagePath = "${comicModel.thumbnail.path}.${comicModel.thumbnail.extension}"
            Picasso.get()
                .load(imagePath)
                .into(imageComic)
        }

    }

}
