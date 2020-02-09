package com.example.viavarejo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.viavarejo.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val version: TextView = root.findViewById(R.id.version)
        val versionCode: TextView = root.findViewById(R.id.version_code)
        homeViewModel.version.observe(this, Observer {
            version.text = it
        })
        homeViewModel.versionCode.observe(this, Observer {
            versionCode.text = it
        })
        return root
    }
}