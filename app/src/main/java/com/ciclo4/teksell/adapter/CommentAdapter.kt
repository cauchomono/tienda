package com.ciclo4.teksell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Comment


class CommentAdapter (val comments:List<Comment>):RecyclerView.Adapter<CommentAdapter.CommentHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): CommentAdapter.CommentHolder {
        val layout = LayoutInflater.from(parent.context)
        return CommentHolder(layout.inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentHolder, position: Int) {
        holder.render(comments[position])
    }

    override fun getItemCount(): Int =  comments.size

    class CommentHolder(val view:View):RecyclerView.ViewHolder(view){

        val tvUser : TextView = view.findViewById(R.id.tvUser)
        val tvComm : TextView = view.findViewById(R.id.tvComment)

        fun render(comment:Comment){
            tvUser.text = (comment.username + ":"+comment.name)
            tvComm.text = comment.comment
        }

    }

}