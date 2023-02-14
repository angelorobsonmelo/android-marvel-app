package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemCharacterLoadMoreStateBinding

class CharacterLoadMoreStateViewHolder(
    itemBinding: ItemCharacterLoadMoreStateBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemCharacterLoadMoreStateBinding.bind(itemView)
    private val progressLoadMoreStateBinding = binding.progressLoadingMore
    private val textTryAgain = binding.textTryAgain.also {
        it.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        progressLoadMoreStateBinding.isVisible = loadState is LoadState.Loading
        textTryAgain.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit) = CharacterLoadMoreStateViewHolder(
            itemBinding = ItemCharacterLoadMoreStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry = retry
        )
    }
}
