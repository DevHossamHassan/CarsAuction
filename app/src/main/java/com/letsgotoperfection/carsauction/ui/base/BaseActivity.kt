package com.letsgotoperfection.chillouttime.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author hossam.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutResourceId(): Int
    abstract fun getTitleResourceId(): String
    abstract fun init()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        title = getTitleResourceId()
        if (savedInstanceState == null) {
            init()
        }
    }

}