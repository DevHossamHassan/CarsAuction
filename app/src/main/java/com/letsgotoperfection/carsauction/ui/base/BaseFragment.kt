package com.letsgotoperfection.chillouttime.ui.base

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author hossam.
 */

abstract class BaseFragment<P : BaseContract.Presenter> : Fragment(), BaseContract.View<Fragment> {

    lateinit var presenter: P
    private lateinit var rootView: View

    @get:LayoutRes
    protected abstract val fragmentLayoutResourceId: Int

    override val viewContext: Fragment
        get() = this

    protected abstract fun init(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(fragmentLayoutResourceId, container, false)
        init(savedInstanceState)
        return rootView
    }

}
