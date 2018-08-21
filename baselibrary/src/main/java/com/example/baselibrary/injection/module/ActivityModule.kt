package com.example.baselibrary.injection.module

import android.support.v7.app.AppCompatActivity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides


/**
 * @author Lai
 * @time 2018/7/19 10:37
 * @Description
 */
@Module
class ActivityModule constructor(private val mActivity: AppCompatActivity) {

    @ActivityScope
    @Provides
    fun provideActivity(): AppCompatActivity {
        return this.mActivity
    }

}