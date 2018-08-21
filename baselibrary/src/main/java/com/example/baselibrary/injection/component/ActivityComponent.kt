package com.example.baselibrary.injection.component

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.baselibrary.injection.module.ActivityModule
import com.kotlin.base.injection.ActivityScope
import dagger.Component

/**
 * @author Lai
 * @time 2018/7/19 10:45
 * @Description
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun getActivity(): AppCompatActivity
    fun context(): Context
}