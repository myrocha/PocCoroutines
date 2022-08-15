package com.example.poccoroutines.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.poccoroutines.data.model.RepositoryResponse
import com.example.poccoroutines.databinding.RepositoryItemBinding
import com.squareup.picasso.Picasso

class RepositoryAdapter() :
    ListAdapter<RepositoryResponse, RepositoryAdapter.RepositoryAdapterViewHolder>(RepositoryItemDiff()) {

    inner class RepositoryAdapterViewHolder(
        private val binding: RepositoryItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryResponse) {
            with(binding) {
                title.text = repository.name
                subTitle.text = repository.description
                Picasso.get().load(repository.owner.avatarUrl).into(icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapterViewHolder {
        val binding =
            RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryAdapterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

private class RepositoryItemDiff : DiffUtil.ItemCallback<RepositoryResponse>() {
    override fun areItemsTheSame(
        oldItem: RepositoryResponse,
        newItem: RepositoryResponse
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RepositoryResponse,
        newItem: RepositoryResponse
    ): Boolean {
        return oldItem == newItem
    }
}