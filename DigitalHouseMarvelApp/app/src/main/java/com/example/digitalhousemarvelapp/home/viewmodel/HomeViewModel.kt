package com.example.digitalhousemarvelapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.digitalhousemarvelapp.home.model.ComicModel
import com.example.digitalhousemarvelapp.home.repository.ComicRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val _repository: ComicRepository
) : ViewModel() {

    private var _comic: List<ComicModel> = listOf()

    fun getComics(title:String? = null)= liveData(Dispatchers.IO) {
        val response =_repository.getComics(title)
        _comic=response.data.results
        emit(response.data.results)
    }


    class HomeViewModelFactory(
        private val repository: ComicRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }

    }
}