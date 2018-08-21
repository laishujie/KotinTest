package com.example.baselibrary.mvp


/**
 * @author Lai
 * @time 2018/7/18 16:53
 * @Description
 */
open class BasePresenter<T : BaseView>(var mView: T?) {

    fun detach() {
        this.mView = null
    }

    //当前依赖的View 是否为空
    fun isViewAttached(): Boolean {
        return mView != null
    }

    fun getActivity() = mView?.getHostActivity()
            ?: throw RuntimeException("Could not call getActivity if the View is not attached")

    open fun onDestroy() {

    }

}