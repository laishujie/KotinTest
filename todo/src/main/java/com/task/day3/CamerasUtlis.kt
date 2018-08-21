package com.task.day3

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.util.Size
import com.orhanobut.logger.Logger
import java.util.*
import java.util.Comparator;

/**
 * @author Lai
 * @time 2018/8/21 11:48
 * @Description
 */
class CamerasUtlis {

    companion object {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun openCamera(context: Context, stateCallback: CameraDevice.StateCallback) {
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            //检测摄像头设备ID，有几个摄像头 第一个是后置，第二个是前置
            val cameraId = cameraManager.cameraIdList[0]
            //获取相机特征对象
            val characteristics = cameraManager.getCameraCharacteristics(cameraId)
            //获取相机输出流配置Map
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

            map?.run {
                val imageDimension = map.getOutputSizes(SurfaceTexture::class.java)[0]
                Logger.w(imageDimension.toString())

                // Add permission for camera and let user grant the permission
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                //调用CameraManger对象打开相机函数
                cameraManager.openCamera(cameraId, stateCallback, null)
            }
        }

    }


}