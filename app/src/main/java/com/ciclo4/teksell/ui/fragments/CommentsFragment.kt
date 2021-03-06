package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.adapter.CommentAdapter
import com.ciclo4.teksell.databinding.FragmentAdminBinding
import com.ciclo4.teksell.databinding.FragmentCommentsBinding
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.network.FirestoreService
import com.ciclo4.teksell.viewmodel.CommentViewModel
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*



class ComentsFragment : Fragment() {

    private var username = ""
    private var name = ""
    private var puntaje:Int=0
    val firestoreService = FirestoreService()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioViewModel  : UsuarioViewModel
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentViewModel  : CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        commentViewModel.refresh()
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.refresh()

        commentAdapter = CommentAdapter()

        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,
                false)
            adapter = commentAdapter
        }

        observeViewModel()
        observeViewModelUser()

        val btnComentar = view.findViewById<Button>(R.id.buttonComentar)
        btnComentar.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            val comment = Comment()
            comment.username = username
            comment.name = name
            comment.comment = view.findViewById<TextInputEditText>(R.id.txtInput).text.toString()
            comment.date = currentDate
            comment.score = puntaje
            commentAdapter.newData(comment)
            firestoreService.addMessage(comment)
            //commentAdapter.updateData(comments)
        }
        btnComentar.setEnabled(false);

        val star1 = view.findViewById<ImageView>(R.id.ivstar1)
        val star2 = view.findViewById<ImageView>(R.id.ivstar2)
        val star3 = view.findViewById<ImageView>(R.id.ivstar3)
        val star4 = view.findViewById<ImageView>(R.id.ivstar4)
        val star5 = view.findViewById<ImageView>(R.id.ivstar5)
        val stars = listOf(star1, star2, star3, star4, star5)
        for(i in stars.indices){
            stars[i].setOnClickListener{
                changeStars(stars, i)
                btnComentar.setEnabled(true);
            }
        }
    }

    fun changeStars(stars:List<ImageView>, on:Int){
        puntaje = on+1
        for (i in stars.indices){
            if(i>on){
                stars[i].setImageResource(R.drawable.star_off)
            }
            else{
                stars[i].setImageResource(R.drawable.star_on)
            }
        }
    }

    fun observeViewModel(){
        commentViewModel.comments.observe(viewLifecycleOwner, Observer<List<Comment>> {
                comments ->  commentAdapter.updateData(comments)
        })
    }

    fun observeViewModelUser(){

        usuarioViewModel.refresh()
        val user = UsuarioViewModel.user
        name = user.name
        username = user.email
    }



}