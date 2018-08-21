package com.banggumi.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.banggumi.R
import com.banggumi.di.component.DaggerBangGuMiComponent
import com.banggumi.mvp.OAuthContract
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.ui.BaseMvpActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * @author Lai
 * @time 2018/7/25 11:26
 * @Description
 */
class BangGuMiActivity : BaseMvpActivity(), OAuthContract.View {


    override fun injectComponent(activityComponent: ActivityComponent) {
        DaggerBangGuMiComponent.builder().activityComponent(activityComponent)
                .oAuthView(this).build().inject(this)
    }


    override fun OAuthBack() {
        toast("OAuthBack")
    }


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {
       /* val presenter1 = getPresenter(OAuthPresenter::class.java)
        com.orhanobut.logger.Logger.e(presenter1.toString())

        presenter1?.getOAuthCode()*/
        find<TextView>(R.id.tv_1).setOnClickListener {
            clipBoard(getHostActivity(),(it as TextView).text.toString())
        }

        find<TextView>(R.id.tv_2).setOnClickListener {
            clipBoard(getHostActivity(),(it as TextView).text.toString())
        }
    }

    fun clipBoard(context: Context, text: String) {
        //获取剪贴板管理器：
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val mClipData = ClipData.newPlainText("Label", text)
        // 将ClipData内容放到系统剪贴板里。
        cm.primaryClip = mClipData

        toast(text)
    }
}