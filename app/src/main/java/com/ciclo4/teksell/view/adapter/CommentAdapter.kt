package com.ciclo4.teksell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Comment

class CommentAdapter ():
    RecyclerView.Adapter<CommentAdapter.CommentHolder>(){

    var listComments = ArrayList<Comment>()


    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): CommentHolder {
        val layout = LayoutInflater.from(parent.context)
        return CommentHolder(layout.inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.render(listComments[getItemCount()-position-1])
    }

    override fun getItemCount(): Int =  listComments.size


    fun updateData(data: List<Comment>) {
        listComments.clear()
        listComments.addAll(data)
        notifyDataSetChanged()
    }

    fun newData(comment: Comment){
        listComments.add(comment)
        notifyDataSetChanged()
    }

    class CommentHolder(val view:View):RecyclerView.ViewHolder(view){

        val tvUser : TextView = view.findViewById(R.id.tvUser)
        val tvComm : TextView = view.findViewById(R.id.tvComment)
        val tvFecha : TextView = view.findViewById(R.id.tvFecha)
        val star1 = view.findViewById<ImageView>(R.id.ivstar1)
        val star2 = view.findViewById<ImageView>(R.id.ivstar2)
        val star3 = view.findViewById<ImageView>(R.id.ivstar3)
        val star4 = view.findViewById<ImageView>(R.id.ivstar4)
        val star5 = view.findViewById<ImageView>(R.id.ivstar5)
        val stars = listOf(star1, star2, star3, star4, star5)


        fun render(comment:Comment){
            tvUser.text = (comment.username + ":"+comment.name)
            tvComm.text = comment.comment
            tvFecha.text =  comment.date
            for(i in stars.indices){
                stars[i].isVisible = i <= comment.score-1
            }
        }

    }

}