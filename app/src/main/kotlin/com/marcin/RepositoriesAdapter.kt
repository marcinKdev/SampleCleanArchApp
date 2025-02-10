package com.marcin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcin.domain.Repository
import com.marcin.samplecleanarch.databinding.ViewholderRepositoryBinding

class RepositoriesAdapter : ListAdapter<Repository, RepositoryViewHolder>(DIFFUTIL) {

      companion object {
            val DIFFUTIL = object : DiffUtil.ItemCallback<Repository>() {
                  override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem.fullName == newItem.fullName
                  override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id
            }
      }

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return RepositoryViewHolder(ViewholderRepositoryBinding.inflate(layoutInflater, parent, false))
      }

      override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
            holder.bind(getItem(position))
      }
}

class RepositoryViewHolder(internal val viewholderRepositoryBinding: ViewholderRepositoryBinding) :
      RecyclerView.ViewHolder(viewholderRepositoryBinding.root) {

      fun bind(repository: Repository) {
            viewholderRepositoryBinding.repositoryName.text = repository.fullName
            viewholderRepositoryBinding.repositoryDesc.text = repository.description
      }

}