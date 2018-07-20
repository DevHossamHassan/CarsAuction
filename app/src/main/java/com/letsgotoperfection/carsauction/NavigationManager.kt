package com.letsgotoperfection.carsauction

/**
 * @author hossam.
 */


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.text.TextUtils

object NavigationManager {

    /**
     * Displays the next fragment
     *
     * @param fragment
     */
    @SuppressLint("ResourceType")
    fun attach(activity: Activity, fragment: Fragment, isAnimated: Boolean, tag: String) {
        if (activity.fragmentManager != null && !isAtTheTopOnBackStack(activity.fragmentManager, tag)) {
            val ft = activity.fragmentManager.beginTransaction()

            if (isAnimated)
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
            if (!TextUtils.isEmpty(tag))
                ft.addToBackStack(tag)
            ft.replace(R.id.container_main_frame_layout, fragment)
            ft.commit()
        }
    }

    @SuppressLint("ResourceType")
    fun attach(activity: Activity, fragment: Fragment, containerId: Int, isAnimated: Boolean, tag: String) {
        if (activity.fragmentManager != null && !isAtTheTopOnBackStack(activity.fragmentManager, tag)) {
            val ft = activity.fragmentManager.beginTransaction()

            if (isAnimated)
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
            if (!TextUtils.isEmpty(tag))
                ft.addToBackStack(tag)
            ft.add(containerId, fragment, tag)
            ft.commit()
        }
    }

    fun attachAsRoot(activity: Activity, fragment: Fragment) {
        if (activity.fragmentManager != null) {
            for (i in 0 until activity.fragmentManager.backStackEntryCount)
                activity.fragmentManager.popBackStackImmediate()
            attach(activity, fragment, false, "")
        }
    }

    /**
     * @return true if stack has been popped succesfully, false if the stack has one element
     */
    fun popBackStackImmediate(mFragmentManager: FragmentManager): Boolean {
        return if (mFragmentManager.backStackEntryCount == 0) {
            false
        } else {
            mFragmentManager.popBackStackImmediate()
            true
        }
    }

    private fun isAtTheTopOnBackStack(mFragmentManager: FragmentManager, tag: String): Boolean {
        return if (mFragmentManager.backStackEntryCount == 0) false
        else TextUtils.equals(mFragmentManager.getBackStackEntryAt(mFragmentManager
                .backStackEntryCount - 1).name, tag)
    }

    /**
     * Navigates back by popping teh back stack. If there is no more items left we finish the
     * current activity.
     *
     * @param baseActivity
     */
    fun navigateBack(baseActivity: Activity) {

        if (baseActivity.fragmentManager.backStackEntryCount == 0) {
            // we can finish the base activity since we have no other fragments
            baseActivity.finish()
        } else {
            baseActivity.fragmentManager.popBackStackImmediate()
        }
    }
}