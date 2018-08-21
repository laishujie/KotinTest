package com.example.baselibrary.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @author Lai
 * @time 2018/7/19 10:33
 * @Description
 */
@Module
class AppModule(private val context :Application){
    
    @Singleton
    @Provides
    fun provideContext():Context{
        return this.context
    }
}