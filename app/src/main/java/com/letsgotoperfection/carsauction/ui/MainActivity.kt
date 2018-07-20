package com.letsgotoperfection.carsauction.ui

import com.letsgotoperfection.carsauction.R
import com.letsgotoperfection.carsauction.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleResourceId(): String {
        return getString(R.string.app_name)
    }

    override fun init() {
        //todo navigate to the main root fragment
    }
}
