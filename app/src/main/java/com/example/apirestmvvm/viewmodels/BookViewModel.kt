package com.example.apirestmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.apirestmvvm.VolumesResponse
import com.example.apirestmvvm.repositorios.BookRepository


class BookSearchViewModel(application: Application) : AndroidViewModel(application) {
    private var bookRepository: BookRepository? = null
    var volumesResponseLiveData: LiveData<VolumesResponse?>? = null
        private set

    fun init() {
        bookRepository = BookRepository()
        volumesResponseLiveData = bookRepository!!.getVolumesResponseLiveData()
    }

    fun searchVolumes(keyword: String?, author: String?) {
        val dotenv: Dotenv = Dotenv.configure().directory("/assets").filename("env").load()
        bookRepository!!.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"))
    }
}