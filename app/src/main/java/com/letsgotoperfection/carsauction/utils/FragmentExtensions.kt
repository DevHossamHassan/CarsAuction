package com.letsgotoperfection.chillouttime.utils

import android.app.Fragment
import android.os.Bundle

/**
 * @author hossam.
 */
inline fun <FRAGMENT : Fragment> FRAGMENT.putArgs(argsBuilder: Bundle.() -> Unit)
        : FRAGMENT = this.apply { arguments = Bundle().apply(argsBuilder) }
