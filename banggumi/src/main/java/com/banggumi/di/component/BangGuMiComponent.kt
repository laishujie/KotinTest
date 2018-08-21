package com.banggumi.di.component

import com.banggumi.di.module.DiOAuthModule
import com.banggumi.mvp.OAuthContract
import com.banggumi.ui.activity.BangGuMiActivity
import com.example.baselibrary.injection.component.ActivityComponent
import com.kotlin.base.injection.PerComponentScope
import dagger.BindsInstance
import dagger.Component


/**
 * @author Lai
 * @time 2018/7/26 15:46
 * @Description
 */
@PerComponentScope
@Component(modules = [(DiOAuthModule::class)])
interface BangGuMiComponent {

    @Component.Builder
    interface Builder {

        // Module所需要的参数可以这样提供
        @BindsInstance
        fun oAuthView(view: OAuthContract.View): Builder

        @BindsInstance
        fun activityComponent(activityComponent: ActivityComponent): Builder

        fun build(): BangGuMiComponent
    }

    fun inject(activityComponent: BangGuMiActivity)
}