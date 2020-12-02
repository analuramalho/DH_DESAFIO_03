package com.example.digitalhousemarvelapp.comic.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.repository.ComicRepository
import com.example.digitalhousemarvelapp.comic.viewmodel.ComicViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class DetailsComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _viewModel: ComicViewModel

    lateinit var imagePath: String

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

        showLoading(true)

        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
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
                showLoading(false)
                titleComic.text = it.title

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    descriptionComic.text =
                        Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT);
                } else {
                    descriptionComic.text = Html.fromHtml(it.description);
                }

                dateComic.text = getString(R.string.Published) + formatDate(it.dates[0].date)
                priceComic.text = getString(R.string.Price) + it.prices[0].price.toString()
                pagesComic.text = getString(R.string.Pages) + it.pageCount.toString()

                if (it.thumbnail == null || it.thumbnail.path.contains("image_not_available")) {
                    Picasso.get()
                        .load(R.drawable.capa)
                        .into(imageComic)
                } else {
                    imagePath = "${it.thumbnail.path}.${it.thumbnail.extension}"
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

        imageComic.setOnClickListener {
            val bundle = bundleOf("URL" to imagePath)
            _view.findNavController()
                .navigate(R.id.action_detailsComicFragment_to_imageComicFragment, bundle)
        }

        setBackNavigation()
    }


    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.detailsComicsLoading)

        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    @SuppressLint("SimpleDateFormat")
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