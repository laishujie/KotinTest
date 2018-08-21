package com.banggumi.mvp.model

import com.banggumi.comm.Api
import com.banggumi.comm.Constant
import com.banggumi.mvp.OAuthContract
import com.example.baselibrary.net.RetrofitFactory
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Lai
 * @time 2018/7/25 16:02
 * @Description
 */

class OAuthService : OAuthContract.Module  {

    override fun getOAuthCode() {


        /*.flatMap {
            RetrofitFactory.instance.create(Api::class.java).getAccessToken("authorization_code",
                    Constant.APP_ID,Constant.APP_SECRET,)
        }*/
    }

}