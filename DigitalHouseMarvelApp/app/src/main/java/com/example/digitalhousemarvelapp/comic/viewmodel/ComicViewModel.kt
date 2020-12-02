package com.example.digitalhousemarvelapp.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.digitalhousemarvelapp.comic.model.ComicModel
import com.example.digitalhousemarvelapp.comic.repository.ComicRepository
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val _repository: ComicRepository
) : ViewModel() {

    private var _comic: List<ComicModel> = listOf()

    fun getComics(title:String? = null)= liveData(Dispatchers.IO) {
        val response =_repository.getComics(title)
        _comic=response.data.results
        emit(response.data.results)
    }

    fun getComicById(comicId: Int) = liveData(Dispatchers.IO) {
        val response = _repository.getComicById(comicId)
        emit(response.data.results[0])
    }

    class HomeViewModelFactory(
        private val repository: ComicRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicViewModel(repository) as T
        }

    }
}