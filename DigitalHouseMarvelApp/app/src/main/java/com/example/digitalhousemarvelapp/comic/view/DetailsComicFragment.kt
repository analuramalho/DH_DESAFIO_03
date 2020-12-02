package com.example.digitalhousemarvelapp.comic.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.repository.ComicRepository
import com.example.digitalhousemarvelapp.comic.viewmodel.ComicViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class DetailsComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _viewModel: ComicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.HomeViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        val imageCoverComic = _view.findViewById<ImageView>(R.id.imageCover_Details)
        val imageComic = _view.findViewById<ImageView>(R.id.image_Details)
        val titleComic = _view.findViewById<TextView>(R.id.textTitleComic_Details)
        val descriptionComic = _view.findViewById<TextView>(R.id.textDescriptionComic_Details)
        val dateComic = _view.findViewById<TextView>(R.id.textPublished_Details)
        val priceComic = _view.findViewById<TextView>(R.id.textPrice_Details)
        val pagesComic = _view.findViewById<TextView>(R.id.textPages_Details)

        val comicId = arguments?.getInt(ComicListFragment.COMIC_ID)

        if (comicId != null) {
            _viewModel.getComicById(comicId).observe(viewLifecycleOwner, Observer {
                titleComic.text = it.title

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    descriptionComic.text =
                        Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT);
                } else {
                    descriptionComic.text = Html.fromHtml(it.description);
                }

                dateComic.text = "Published: ${formatDate(it.dates[0].date)}"
                priceComic.text = "Price: $ ${it.prices[0].price.toString()}"
                pagesComic.text = "Pages: ${it.pageCount.toString()}"

                if (it.thumbnail == null || it.thumbnail.path.contains("image_not_available")) {
                    Picasso.get()
                        .load(R.drawable.capa)
                        .into(imageComic)
                } else {
                    val imagePath = "${it.thumbnail.path}.${it.thumbnail.extension}"
                    Picasso.get()
                        .load(imagePath)
                        .into(imageComic)

                    val imagePathCover =
                        "${it.thumbnail.path}/landscape_large.${it.thumbnail.extension}"
                    Picasso.get()
                        .load(imagePathCover)
                        .into(imageCoverComic)
                }
            })
        }

        setBackNavigation()
    }

    private fun formatDate(date: String?): String? {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val format = SimpleDateFormat("MMMM dd,yyyy")

        return format.format(parser.parse(date))
    }

    private fun setBackNavigation() {
        _view.findViewById<ImageView>(R.id.imgBack_Details).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

}