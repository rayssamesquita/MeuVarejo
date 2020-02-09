package com.example.viavarejo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viavarejo.BuildConfig
import com.example.viavarejo.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeViewModel : ViewModel() {

    private val _version = MutableLiveData<String>().apply {
        value = "VERSION (" + BuildConfig.VERSION_NAME + ")"
    }
    val version: LiveData<String> = _version

    private val _versionCode = MutableLiveData<String>().apply {
        value = "version_code (" + BuildConfig.VERSION_CODE + ")"
    }

    val versionCode: LiveData<String> = _versionCode
}