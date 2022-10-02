package com.example.appforportfolio.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appforportfolio.databinding.ItemCharacterBinding
import com.example.appforportfolio.databinding.ItemCharacterFavouritesBinding
import com.example.domain.model.Character

class CharacterFavouritesAdapter(
    private val onClick: (Character) -> Unit
) : ListAdapter<Character, CharacterViewHolder>(CharacterDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterFavouritesBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }
}

class CharacterViewHolder(
    private val binding: ItemCharacterFavouritesBinding,
    private val onClick: (Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Character) {
        with(binding) {
            imgAvatar.load(item.image)
            tvCharacterName.text = item.name
            tvCharacterSpecies.text = item.species
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
}

private class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}