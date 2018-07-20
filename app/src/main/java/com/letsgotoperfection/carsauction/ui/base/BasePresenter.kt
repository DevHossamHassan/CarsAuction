package com.letsgotoperfection.chillouttime.ui.base

import java.lang.ref.WeakReference

/**
 * @author hossam.
 */

abstract class BasePresenter<V : BaseContract.View<*>>(view: V) : BaseContract.Presenter {
    protected var view: WeakReference<V> = WeakReference(view)
}
