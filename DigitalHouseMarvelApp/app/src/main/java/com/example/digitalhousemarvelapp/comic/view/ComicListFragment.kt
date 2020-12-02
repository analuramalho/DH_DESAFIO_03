package com.example.digitalhousemarvelapp.comic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.comic.model.ComicModel
import com.example.digitalhousemarvelapp.comic.repository.ComicRepository
import com.example.digitalhousemarvelapp.comic.viewmodel.ComicViewModel

class ComicListFragment : Fragment() {
    private lateinit var _view:View
    private lateinit var _viewModel:ComicViewModel
    private lateinit var _listAdapter: ComicAdapter
    private lateinit var _recyclerView:RecyclerView

    private var _comics = mutableListOf<ComicModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view =view

        val list = _view.findViewById<RecyclerView>(R.id.listComics)
        val manager = GridLayoutManager(_view.context,3)

        _comics = mutableListOf<ComicModel>()
        _listAdapter= ComicAdapter(_comics){
            val bundle= bundleOf(COMIC_ID to it.id)
            _view.findNavController().navigate(R.id.action_comicListFragment_to_detailsComicFragment, bundle)
        }

        _recyclerView=_view.findViewById<RecyclerView>(R.id.listComics)

        list.apply {
            setHasFixedSize(true)
            layoutManager=manager
            adapter=_listAdapter
        }

        _viewModel=ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        _viewModel.getComics().observe(viewLifecycleOwner, Observer {
            showResults(it)
        })

        _viewModel.getComics()
        showLoading(true)

    }

    private fun showResults(list:List<ComicModel>?){
        showLoading(false)
        list?.let{_comics.addAll(it)}
        _listAdapter.notifyDataSetChanged()
    }


    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.listComicsLoading)

        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }

    companion object{
        const val COMIC_ID="COMIC_ID"
    }
}

