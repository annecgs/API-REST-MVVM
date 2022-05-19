package com.example.apirestmvvm.adapters

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirestmvvm.Volume
import okhttp3.internal.Util
import kotlinx.coroutines.flow.collect


class BookSearchResultsAdapter :
    RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder>() {
    private var results: List<Volume> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookSearchResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookSearchResultHolder, position: Int) {
        val volume = results[position]
        holder.titleTextView.text = volume.volumeInfo!!.getTitle()
        holder.publishedDateTextView.text = volume.volumeInfo!!.getPublishedDate()
        if (volume.volumeInfo!!.getImageLinks() != null) {
            val imageUrl = volume.volumeInfo!!.getImageLinks()!!.getSmallThumbnail()
                ?.replace("http://", "https://")
            Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.smallThumbnailImageView)
        }
        if (volume.volumeInfo!!.getAuthors() != null) {
            val u = Util()
            val authors: String = u.StringJoin(volume.volumeInfo!!.getAuthors(), ", ")
            holder.authorsTextView.text = authors
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(results: List<Volume>) {
        this.results = results
        notifyDataSetChanged()
    }

    inner class BookSearchResultHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView
        val authorsTextView: TextView
        val publishedDateTextView: TextView
        val smallThumbnailImageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.book_item_title)
            authorsTextView = itemView.findViewById(R.id.book_item_authors)
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate)
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail)
        }
    }
}