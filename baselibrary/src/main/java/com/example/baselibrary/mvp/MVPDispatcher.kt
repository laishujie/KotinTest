package com.example.baselibrary.mvp


/**
 * @author Lai
 * @time 2018/7/23 15:44
 * @Description Presenter分发
 */
class MVPDispatcher {
    //
    private val presenters: ArrayList<BasePresenter<*>> = arrayListOf()

    //添加Presenter
    fun <V : BaseView> addPresenter(presenter: BasePresenter<V>) {
        if (presenter.isViewAttached() && !presenters.contains(presenter)) {
            presenters.add(presenter)
        }
    }

    //
    private fun <V : BaseView> removePresenter(presenter: BasePresenter<V>) {
        if (presenters.contains(presenter)) {
            presenters.remove(presenter)
            if (presenter.isViewAttached()) {
                presenter.detach()
            }
        }
    }

    fun dispatchOnDestroy() {
        presenters.forEach({
            if (it.isViewAttached()) {
                it.onDestroy()
            }
            removePresenter(it)
        })
    }


}
