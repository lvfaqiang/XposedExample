package com.fq.demo.xposed.hook

import android.os.Bundle
import android.widget.TextView
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * XposedInit2019/3/12 3:14 PM
 * @desc :
 *
 */
class XposedInit : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam?.packageName == "com.fq.demo.xposed") {

            DeviceHook.hook()

            XposedHelpers.findAndHookMethod("com.fq.demo.xposed.feature.MainActivity", lpparam.classLoader, "onCreate", Bundle::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    XposedBridge.log("test demo beforeHookedMethod")
                }

                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                    val c = lpparam.classLoader.loadClass("com.fq.demo.xposed.feature.MainActivity")
                    val m = c.getDeclaredMethod("getTvLabel")
                    m.isAccessible = true
                    XposedBridge.log("test demo afterHookedMethod")
                    val textView = m.invoke(param!!.thisObject) as TextView
                    textView.text = "xposed method after"
                }
            })
        }

    }
}