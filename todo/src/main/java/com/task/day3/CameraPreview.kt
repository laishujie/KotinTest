package com.task.day3

import android.content.Context
import android.hardware.Camera
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView


/**
 * @author Lai
 * @time 2018/8/21 11:16
 * @Description
 */
class CameraPreview :SurfaceView,SurfaceHolder.Callback{

    private  lateinit var mSurfaceHolder: SurfaceHolder

    private lateinit var mCamera: Camera


    constructor(mContext: Context,camera: Camera) : super(mContext){
        mCamera = camera
        mSurfaceHolder = holder
        mSurfaceHolder.addCallback(this)

    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
    }

}