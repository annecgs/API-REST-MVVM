package com.example.apirestmvvm.views

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestmvvm.VolumesResponse
import com.example.apirestmvvm.adapters.BookSearchResultsAdapter
import com.google.android.material.textfield.TextInputEditText
import java.security.AccessController.getContext
import kotlinx.coroutines.flow.collect


class BookSearchFragment : Fragment() {
    private var viewModel: BookSearchViewModel? = null
    private var adapter: BookSearchResultsAdapter? = null
    private var keywordEditText: TextInputEditText? = null
    private var authorEditText: TextInputEditText? = null
    private var searchButton: Button? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BookSearchResultsAdapter()
        viewModel = ViewModelProviders.of(this).get(BookSearchViewModel::class.java)
        viewModel!!.init()
        viewModel!!.getVolumesResponseLiveData().observe(this, object : Observer<VolumesResponse?> {
            override fun onChanged(volumesResponse: VolumesResponse?) {
                if (volumesResponse != null) {
                    adapter!!.setResults(volumesResponse.items!!)
                }
            }
        })
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_booksearch, container, false)
        val recyclerView: RecyclerView =
            view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword)
        authorEditText = view.findViewById(R.id.fragment_booksearch_author)
        searchButton = view.findViewById(R.id.fragment_booksearch_search)
        searchButton.setOnClickListener(object : View.OnClickListener() {
            override fun onClick(v: View?) {
                performSearch()
            }
        })
        return view
    }

    fun performSearch() {
        val keyword = keywordEditText!!.editableText.toString()
        val author = authorEditText!!.editableText.toString()
        viewModel?.searchVolumes(keyword, author)
    }
}

private fun ViewModelProvider.get(java: Class<BookSearchViewModel>) {

}

class BookSearchViewModel {
    fun getVolumesResponseLiveData() {

    }

    fun searchVolumes(keyword: String, author: String) {

    }

    fun init() {
        TODO("Not yet implemented")
    }

}
