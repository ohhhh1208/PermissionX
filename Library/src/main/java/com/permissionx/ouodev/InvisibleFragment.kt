package com.permissionx.ouodev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Created by oxq on 2022/6/8.
 * typealias关键字可以用于给任意类型指定一个别名
 */

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    //callback变量作为运行时权限申请结果的回调通知方式，并将它声明成了一种函数类型变量
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }

        }

    }
}