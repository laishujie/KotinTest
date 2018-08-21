package com.banggumi.di.module

import com.banggumi.mvp.OAuthContract
import com.banggumi.mvp.presenter.OAuthPresenter
import com.example.baselibrary.mvp.BasePresenter
import com.kotlin.base.injection.PerComponentScope
import dagger.Module
import dagger.Provides


/**
 * @author Lai
 * @time 2018/7/26 15:05
 * @Description
 */
@Module
class DiOAuthModule {

    @Provides
    @PerComponentScope
    internal fun providePresenters(oAuthPresenter: OAuthPresenter): Array<BasePresenter<*>> {
        return arrayOf(oAuthPresenter)
    }

    @PerComponentScope
    @Provides
    internal fun provideOAuthPresenter(view: OAuthContract.View): OAuthPresenter {
        return OAuthPresenter(view)
    }

}
