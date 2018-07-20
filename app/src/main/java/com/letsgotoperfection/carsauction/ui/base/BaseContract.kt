package com.letsgotoperfection.chillouttime.ui.base

/**
 * @author hossam.
 */
interface BaseContract {
    interface View<C> {
        val viewContext: C
    }

    interface Presenter
}
