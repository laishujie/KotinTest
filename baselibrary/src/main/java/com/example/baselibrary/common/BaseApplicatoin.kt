package com.example.baselibrary.common

import android.app.Application
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.Log
import com.example.baselibrary.injection.component.AppComponent
import com.example.baselibrary.injection.component.DaggerAppComponent
import com.example.baselibrary.injection.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogStrategy
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * @author Lai
 * @time 2018/7/19 10:50
 * @Description
 */
open class BaseApplicatoin : Application() {

    //基类
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        initLogger()
    }

    private fun initLogger() {
        val logStrategy = object : LogStrategy {

            private val prefix = arrayOf(". ", " .")
            private var index = 0

            override fun log(priority: Int, @Nullable tag: String?, @NonNull message: String) {
                index = index xor 1
                Log.println(priority, prefix[index] + tag!!, message)
            }
        }

        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .logStrategy(logStrategy)
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(0)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("BangGuMi") //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build()
        com.orhanobut.logger.Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}