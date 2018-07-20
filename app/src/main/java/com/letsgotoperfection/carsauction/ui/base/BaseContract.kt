package com.letsgotoperfection.carsauction.ui.base

/**
 * @author hossam.
 */
interface BaseContract {
    interface View<C> {
        val viewContext: C
    }

    interface Presenter
}
