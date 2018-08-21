package com.banggumi.mvp.presenter

import com.banggumi.comm.Api
import com.banggumi.comm.Constant
import com.banggumi.mvp.OAuthContract
import com.example.baselibrary.mvp.BasePresenter
import com.example.baselibrary.net.RetrofitFactory
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Lai
 * @time 2018/7/26 10:46
 * @Description
 */
class OAuthPresenter constructor(mView: OAuthContract.View) : BasePresenter<OAuthContract.View>(mView), OAuthContract.Module {


    override fun getOAuthCode() {
        RetrofitFactory.createApi(Api::class.java).getOAuthCode(Constant.APP_ID, "code")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.e(it.toString())
                })
        //module.getOAuthCode()\
    }

}