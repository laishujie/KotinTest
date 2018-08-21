package com.example.baselibrary.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.baselibrary.common.BaseApplicatoin
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.mvp.BasePresenter
import com.example.baselibrary.mvp.BaseView
import com.example.baselibrary.mvp.MVPDispatcher
import com.kotlin.base.widgets.ProgressLoading
import javax.inject.Inject


/**
 * @author Lai
 * @time 2018/7/18 17:03
 * @Description
 */
abstract class BaseMvpActivity : BaseActivity(), BaseView {

    //泛型注入
    @Inject
    lateinit var mPresenters: Array<BasePresenter<*>>

    //mvp分发处理
    private val mvpDispatcher = MVPDispatcher()


    //提供Activity 公共的ActivityCompoent
    lateinit var mActivityComponent: ActivityComponent

    //加载的loading
    private lateinit var mLoading: ProgressLoading


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplicatoin).appComponent)
                .activityModule(ActivityModule(this))
                .build()

        injectComponent(mActivityComponent)

        setContentView(getLayout())

        //loading
        mLoading = ProgressLoading.create(this)

        //添加p层
        mPresenters.forEach {
            mvpDispatcher.addPresenter(it)
        }

        initData(savedInstanceState)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : BasePresenter<*>> getPresenter(clazz: Class<T>): T? {
        mPresenters.forEach {
            if (it.javaClass.name == clazz.name) {
                return it as T
            }
        }
        return null
    }


    override fun getHostActivity(): AppCompatActivity {
        return mActivityComponent.getActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDispatcher.dispatchOnDestroy()
    }


    /*
        Dagger注册
     */
    protected abstract fun injectComponent(activityComponent: ActivityComponent)

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)

    override fun onError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        mLoading.showLoading()
    }

    override fun hideLoading() {
        mLoading.hideLoading()
    }

}