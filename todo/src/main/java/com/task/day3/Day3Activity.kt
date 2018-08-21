package com.task.day3

import android.Manifest
import android.annotation.TargetApi
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.Image
import android.media.ImageReader
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Surface
import android.view.TextureView
import com.example.baselibrary.common.rxpermissions2.RxPermissions
import com.example.baselibrary.ui.BaseActivity
import com.example.todo.R
import com.orhanobut.logger.Logger
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * @author Lai
 * @time 2018/8/21 15:21
 * @Description
 */
class Day3Activity : BaseActivity() {

    private lateinit var textureView: TextureView

    private lateinit var captureRequestBuilder: CaptureRequest.Builder


    private lateinit var cameraCaptureSessions: CameraCaptureSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_day_3)
        textureView = find(R.id.tv_preview)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RxPermissions(this).request(Manifest.permission.CAMERA)
                    .subscribe({
                        if (it) {
                            textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                                override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
                                }

                                override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
                                }

                                override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
                                    return false
                                }

                                override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        CamerasUtlis.openCamera(this@Day3Activity, stateCallback)
                                    }
                                }
                            }
                            //打开摄像头
                        } else {
                            toast("请允许！！")
                        }
                    })
        }

    }

    private val stateCallback = @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice?) {
            //创建预览
            createPreview(camera)
        }

        override fun onDisconnected(camera: CameraDevice?) {
            camera?.close()
        }

        override fun onError(camera: CameraDevice?, error: Int) {
            toast("OnError: $error")
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createPreview(camera: CameraDevice?) {

        camera?.run {

            val texture = textureView.surfaceTexture

            //设置SurfaceTexture默认的缓冲区大小，为上面得到的预览的size大小
            texture.setDefaultBufferSize(textureView.width, textureView.height)
            val surface = Surface(texture)
            //创建CaptureRequest对象，并且声明类型为TEMPLATE_PREVIEW，可以看出是一个预览类型
            captureRequestBuilder = this.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            //设置请求的结果返回到到Surface上
            captureRequestBuilder.addTarget(surface)
            captureRequestBuilder.addTarget(getImageReader().surface)

            camera.createCaptureSession(mutableListOf(surface), object : CameraCaptureSession.StateCallback() {
                override fun onConfigureFailed(session: CameraCaptureSession?) {
                    toast("初始化失败")
                }

                override fun onConfigured(session: CameraCaptureSession?) {
                    session?.run {
                        this@Day3Activity.cameraCaptureSessions = session
                        // When the session is ready, we start displaying the preview.
                        //更新预览
                        updatePreview()
                    }
                }

            }, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updatePreview() {
        //设置相机的控制模式为自动，方法具体含义点进去（auto-exposure, auto-white-balance, auto-focus）
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

        try {
            //设置重复捕获图片信息
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getImageReader(): ImageReader {
        val newInstance = ImageReader.newInstance(textureView.width, textureView.height, ImageFormat.YUV_420_888, 2)
        newInstance.setOnImageAvailableListener({
            var image: Image? = null
            try {
                image = newInstance.acquireLatestImage()

                //获取图片byte数组
                val planes = image.planes
                val buffer = planes[0].buffer
                buffer.rewind()
                val data = ByteArray(buffer.capacity())
                buffer.get(data)
                data.size
                Logger.w("data size : " + data.size)
            } finally {
                image?.close()
            }

        }, null)
        return newInstance
    }

}