package com.fq.demo.xposed.hook

import android.os.Build
import com.fq.demo.xposed.Constants
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

/**
 * DeviceHook2019/3/12 4:14 PM
 * @desc :
 *  修改设备型号
 */
object DeviceHook {

    private var deviceBrand = "nokia"    //手机品牌
    private var product = "001" // 型号
    private var deviceModel = "nokia 001"
    private var systemVersion = "10.0.1"    // 系统版本号

    fun hook() {
        XposedHelpers.setStaticObjectField(Build::class.java, Constants.MODEL, deviceModel)   // 手机型号
        XposedHelpers.setStaticObjectField(Build.VERSION::class.java, Constants.RELEASE, systemVersion) // 系统版本号
        XposedHelpers.setStaticObjectField(Build::class.java, Constants.MANUFACTURER, deviceBrand)
        XposedHelpers.setStaticObjectField(Build::class.java, Constants.BRAND, deviceBrand) // 品牌
        XposedHelpers.setStaticObjectField(Build::class.java, Constants.PRODUCT, product)   // 型号
        XposedHelpers.setStaticObjectField(Build::class.java, Constants.DEVICE, product)    // 型号
//       ...

        //应对反射获取机型的情况
        val c = Class.forName("android.os.SystemProperties")
        val m = c.getDeclaredMethod("native_get", String::class.java, String::class.java)
        m.isAccessible = true
        XposedBridge.hookMethod(m, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                when (param.args[0].toString()) {
                    "ro.product.manufacturer", "ro.product.brand" -> param.result = deviceBrand
                    "ro.product.name", "ro.product.device" -> param.result = product
                    "ro.product.model" -> param.result = deviceModel
                }
            }
        })
    }
}