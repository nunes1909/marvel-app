package com.example.marvelapp.view.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelapp.databinding.ItemComicBinding
import com.example.marvelapp.util.extensions.limitDescription
import com.example.marvelapp.view.comic.model.ComicView

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differ = object : DiffUtil.ItemCallback<ComicView>() {
        override fun areItemsTheSame(oldItem: ComicView, newItem: ComicView): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ComicView, newItem: ComicView): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path &&
                    oldItem.thumbnail.extension == newItem.thumbnail.extension
        }
    }

    private val asyncDiffer = AsyncListDiffer(this, differ)

    var comics: List<ComicView>
        get() = asyncDiffer.currentList
        set(value) = asyncDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = comics.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.apply {
            itemComicTitulo.text = comic.title.limitDescription(20)
            itemComicDescricao.text = comic.description?.limitDescription(80)
            itemComicImage.load(
                comic.thumbnail.path + "." + comic.thumbnail.extension
            )
        }
    }
}
