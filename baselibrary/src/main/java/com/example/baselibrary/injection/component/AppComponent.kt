package com.example.baselibrary.injection.component

import android.content.Context
import com.example.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton


/**
 * @author Lai
 * @time 2018/7/19 10:43
 * @Description
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    //暴露方法
    fun context(): Context
}