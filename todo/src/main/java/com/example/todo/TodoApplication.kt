package com.example.todo

import com.example.baselibrary.common.BaseApplicatoin
import com.todo.greendao.DaoMaster
import com.todo.greendao.DaoSession


/**
 * @author Lai
 * @time 2018/8/10 11:27
 * @Description
 */
class TodoApplication : BaseApplicatoin() {

    companion object {                          //声明静态变量。
        lateinit var daoSession: DaoSession
    }

    override fun onCreate() {
        super.onCreate()
        initDao()
    }

    private fun initDao() {
        val helper = DaoMaster.DevOpenHelper(this, "Todo")//创建的数据库名。
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }


}