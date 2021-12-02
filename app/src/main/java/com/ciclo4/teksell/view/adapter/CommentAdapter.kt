package com.ciclo4.teksell.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Comment


class CommentAdapter (val commentsListener: CommentsListener):RecyclerView.Adapter<CommentAdapter.CommentHolder>(){

    var listComments = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): CommentAdapter.CommentHolder {
        val layout = LayoutInflater.from(parent.context)
        return CommentHolder(layout.inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentHolder, position: Int) {
        val comment = listComments[position]
        holder.tvUser.text = (comment.username + ":"+ comment.name)
        holder.tvComm.text = comment.comment
    }

    override fun getItemCount(): Int =  listComments.size

    fun updateData(data: List<Comment>) {
        listComments.clear()
        listComments.addAll(data)
        notifyDataSetChanged()
    }

    class CommentHolder(val view:View):RecyclerView.ViewHolder(view){

        val tvUser : TextView = view.findViewById(R.id.tvUser)
        val tvComm : TextView = view.findViewById(R.id.tvComment)


    }

}