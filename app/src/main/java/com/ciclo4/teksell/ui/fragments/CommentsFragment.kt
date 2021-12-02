package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.view.adapter.CommentAdapter
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.view.adapter.CommentsListener
import com.ciclo4.teksell.viewmodel.CommentViewModel
import com.google.android.material.textfield.TextInputEditText



class ComentsFragment : Fragment(), CommentsListener {

//    private lateinit var commentAdapter: CommentAdapter
//    private lateinit var commentViewModel: CommentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comments, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnComentar = view.findViewById<Button>(R.id.buttonComentar)
//
//        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
//        commentViewModel.getCommentsFromFirebase()
//
//        commentAdapter = CommentAdapter(this)
//
//        val  rvComment: RecyclerView = view.findViewById(R.id.rvComments)

//        rvComment.apply{
//            layoutManager = LinearLayoutManager(view.context)
//            adapter = commentAdapter
//        }
//
//        observeViewModel()
//    }

//    fun observeViewModel(){
//        commentViewModel.listComments.observe(viewLifecycleOwner, Observer<List<Comment>> {
//            comments ->
//            commentAdapter.updateData(comments)
//        })
    }





}