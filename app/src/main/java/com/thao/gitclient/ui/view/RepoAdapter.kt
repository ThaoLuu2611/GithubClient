package com.thao.gitclient.ui.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thao.gitclient.R
import com.thao.gitclient.model.Repo

class RepoAdapter constructor(
    private var itemList: List<Repo>,
    private var context: Context?,
) : Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        itemList?.let {
            holder.bind(it[position])
        }
    }
}