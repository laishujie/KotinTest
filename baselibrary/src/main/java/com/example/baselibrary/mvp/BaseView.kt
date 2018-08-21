package com.example.baselibrary.mvp

import android.support.v7.app.AppCompatActivity

interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
    fun getHostActivity():AppCompatActivity
}