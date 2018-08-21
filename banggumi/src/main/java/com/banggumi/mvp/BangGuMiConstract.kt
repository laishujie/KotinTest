package com.banggumi.mvp

import com.example.baselibrary.mvp.BaseView


/**
 * @author Lai
 * @time 2018/7/25 16:45
 * @Description
 */
interface OAuthContract {
    interface View : BaseView {
        fun OAuthBack()
    }

    interface Module {
        fun getOAuthCode()
    }
}