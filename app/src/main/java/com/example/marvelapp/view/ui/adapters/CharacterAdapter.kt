package com.example.marvelapp.view.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCharacterBinding
import com.example.marvelapp.util.extensions.limitDescription
import com.example.marvelapp.view.character.model.CharacterView

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differ = object : DiffUtil.ItemCallback<CharacterView>() {
        override fun areItemsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path &&
                    oldItem.thumbnail.extension == newItem.thumbnail.extension

        }
    }

    private val asyncDiffer = AsyncListDiffer(this, differ)

    var characters: List<CharacterView>
        get() = asyncDiffer.currentList
        set(value) = asyncDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.apply {
            itemCharacterNome.text = character.name

            if (character.description.isEmpty()) {
                itemCharacterDescricao.text =
                    holder.itemView.context.getString(R.string.descricao_padrao)
            } else {
                itemCharacterDescricao.text = character.description.limitDescription(80)
            }

            itemCharacterImage.load(
                character.thumbnail.path + "." + character.thumbnail.extension
            )
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(character)
            }
        }
    }

    private var onItemClickListener: ((CharacterView) -> Unit)? = null

    fun setOnItemClickListener(listener: (CharacterView) -> Unit) {
        onItemClickListener = listener
    }

    fun getCharacterPosition(position: Int): CharacterView {
        return characters[position]
    }
}