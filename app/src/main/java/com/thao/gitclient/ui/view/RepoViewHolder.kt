package com.thao.gitclient.ui.view

import android.view.View
import com.thao.gitclient.Utils.Utils
import kotlinx.android.synthetic.main.item_repo.view.*

import com.thao.gitclient.model.Repo

class RepoViewHolder(
    itemView: View,
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){

    fun bind(repo: Repo) {
        itemView.tvRepoName.text = repo.name
        itemView.tvLang.text = repo.language
        itemView.tvDescription.text = repo.description
        itemView.tvPrivacy.text = if(repo.private == true) "Private" else "Public"
        itemView.tvStar.text = repo.stargazers_count.toString()
        repo.updated_at?.let {
            itemView.tvUpdatedDate.text = Utils.formatDate(it)
        }

    }
}