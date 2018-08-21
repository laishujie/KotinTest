package com.task.day1

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.todo.R
import com.orhanobut.logger.Logger


/**
 * @author Lai
 * @time 2018/8/20 11:42
 * @Description SurfaceView 显示图片
 */
class SurfaceViewTemplate : SurfaceView, SurfaceHolder.Callback, Runnable {


    private lateinit var mSurfaceHolder: SurfaceHolder
    //绘图的Canvas
    private lateinit var mCanvas: Canvas
    //子线程标志位
    private var mIsDrawing: Boolean = false
    private lateinit var mPaint: Paint

    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, mAttributeSet: AttributeSet?) : this(mContext, mAttributeSet, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }


    //改变
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Logger.w("surfaceChanged")
    }

    //销毁
    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
        Logger.w("surfaceDestroyed")
    }

    //创建的时候调用
    override fun surfaceCreated(holder: SurfaceHolder?) {
        mIsDrawing = true
        Thread(this).run()
    }

    //绘图逻辑
    private fun drawSomething() {
        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas()
            //绘制背景
            mCanvas.drawColor(Color.WHITE)

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.anifly_bg_frame_trim_rab_1)
            //绘图
            mCanvas.drawBitmap(bitmap, 0f, 0f, mPaint)

            Logger.w("draw。....")

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mCanvas.run {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas)
            }
        }
    }

    /**
     * 初始化View
     */
    private fun initView() {
        mSurfaceHolder = holder
        //注册回调方法
        mSurfaceHolder.addCallback(this)
        //是否可以获取焦点
        isFocusable = true
        //屏幕常亮
        keepScreenOn = true
        //是否可以获取焦点
        isFocusableInTouchMode = true

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun run() {
        drawSomething()
    }
}