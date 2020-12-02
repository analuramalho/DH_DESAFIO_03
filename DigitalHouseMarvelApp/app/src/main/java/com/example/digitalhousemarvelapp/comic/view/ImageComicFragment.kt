package com.example.digitalhousemarvelapp.comic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.repository.ComicRepository
import com.example.digitalhousemarvelapp.comic.viewmodel.ComicViewModel
import com.squareup.picasso.Picasso


class ImageComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _viewModel: ComicViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        val imageFullScreen = _view.findViewById<ImageView>(R.id.image_ImageComicFullScreen)
        val comicId = arguments?.getString("URL")

        Picasso.get()
            .load(comicId)
            .into(imageFullScreen)

        setCloseNavigation()
    }


    private fun setCloseNavigation() {
        _view.findViewById<ImageView>(R.id.imgClose_ImageComicFullScreen).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }
}