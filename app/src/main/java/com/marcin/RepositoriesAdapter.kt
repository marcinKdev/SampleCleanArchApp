package com.marcin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marcin.domain.Repository
import com.marcin.samplecleanarch.R

class RepositoriesAdapter : ListAdapter<Repository, RepositoryViewHolder>(DIFFUTIL) {

            companion object {
                        val DIFFUTIL = object : DiffUtil.ItemCallback<Repository>() {
                                    override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem.fullName == newItem.fullName
                                    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id
                        }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
                        val layoutInflater = LayoutInflater.from(parent.context)
                        return RepositoryViewHolder(layoutInflater.inflate(R.layout.viewholder_repository, parent, false))
            }

            override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
                        holder.bind(getItem(position))
            }
}

class RepositoryViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(repository: Repository) {
                        val repoName = itemView.findViewById<TextView>(R.id.repositoryName)
                        val repoDesc = itemView.findViewById<TextView>(R.id.repositoryDesc)

                        repoName.text = repository.fullName
                        repoDesc.text = repository.description
            }

}