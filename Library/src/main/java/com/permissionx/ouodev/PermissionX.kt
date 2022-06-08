package com.permissionx.ouodev

import androidx.fragment.app.FragmentActivity

/**
 * Created by oxq on 2022/6/8.
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //*表示将一个数组转换成可变长度参数传递过去
        fragment.requestNow(callback, *permission)
    }

}