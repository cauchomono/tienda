package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.FragmentStoreProfileBinding
import com.ciclo4.teksell.model.Ubication
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng


class ProfileFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentStoreProfileBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(p0: GoogleMap) {
        val ubication = Ubication()
        val zoom = 15f
        val centerMap = LatLng(ubication.latitude, ubication.longitude)
        p0.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))
    }
}