package com.example.digitalhousemarvelapp.home.view

import android.media.Image
import android.os.Bundle
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalhousemarvelapp.R
import com.example.digitalhousemarvelapp.home.model.ComicModel
import com.example.digitalhousemarvelapp.home.repository.ComicRepository
import com.example.digitalhousemarvelapp.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var _view:View
    private lateinit var _viewModel:HomeViewModel
    private lateinit var _listAdapter: HomeAdapter
    private lateinit var _recyclerView:RecyclerView

    private var _comics = mutableListOf<ComicModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view =view

        val list = _view.findViewById<RecyclerView>(R.id.listComics)
        val manager = GridLayoutManager(_view.context,3)

        _comics = mutableListOf<ComicModel>()
        _listAdapter= HomeAdapter(_comics){
            val bundle= bundleOf(COMIC_ID to it.id)
            _view.findNavController().navigate(R.id.action_homeFragment_to_detailsComicFragment, bundle)
        }

        _recyclerView=_view.findViewById<RecyclerView>(R.id.listComics)

        list.apply {
            setHasFixedSize(true)
            layoutManager=manager
            adapter=_listAdapter
        }

        _viewModel=ViewModelProvider(
            this,
            HomeViewModel.HomeViewModelFactory(ComicRepository())
        ).get(HomeViewModel::class.java)

        _viewModel.getComics().observe(viewLifecycleOwner, Observer {
            showResults(it)
        })

        _viewModel.getComics()
    }

    private fun showResults(list:List<ComicModel>?){
        list?.let{_comics.addAll(it)}
        _listAdapter.notifyDataSetChanged()
    }


    companion object{
        const val COMIC_ID="COMIC_ID"
    }
}

