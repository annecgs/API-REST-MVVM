package com.example.apirestmvvm.repositorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apirestmvvm.VolumesResponse
import com.example.apirestmvvm.apis.BookSearchService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {
    private val BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/"

    private var bookSearchService: BookSearchService? = null
    private var volumesResponseLiveData: MutableLiveData<VolumesResponse>? = null

    fun BookRepository() {
        volumesResponseLiveData = MutableLiveData()
        val interceptor = HttpLoggingInterceptor()
        val level = interceptor.level()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        bookSearchService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookSearchService::class.java)
    }

    fun searchVolumes(keyword: String?, author: String?, apiKey: Unit) {
        bookSearchService!!.searchVolumes(keyword, author, apiKey)
            ?.enqueue(object : Callback<VolumesResponse?> {
                override fun onResponse(
                    call: Call<VolumesResponse?>?,
                    response: Response<VolumesResponse?>,
                ) {
                    if (response.body() != null) {
                        volumesResponseLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<VolumesResponse?>?, t: Throwable?) {
                    volumesResponseLiveData!!.postValue(null)
                }
            })
    }

    fun getVolumesResponseLiveData(): LiveData<VolumesResponse?>? {
        return volumesResponseLiveData
    }
}

class HttpLoggingInterceptor : Interceptor {
    class Level

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        TODO("Not yet implemented")
    }

    fun level() {
    }
}
