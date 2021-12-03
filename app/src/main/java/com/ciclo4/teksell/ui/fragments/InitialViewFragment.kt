package com.ciclo4.teksell.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.FragmentInitialViewBinding
import com.ciclo4.teksell.ui.activities.MainActivity


class InitialViewFragment : Fragment() {


    private var _binding: FragmentInitialViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInitialViewBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

    }
}