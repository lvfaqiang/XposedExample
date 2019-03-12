package com.fq.demo.xposed.hook;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * XposedInit2019/3/12 9:23 AM
 *
 * @desc :
 */
public class XposedInit implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("test demo beforeHookedMethod--------");
        if (lpparam.packageName.equals("com.fq.demo.xposed")) {
            XposedHelpers.findAndHookMethod("com.fq.demo.xposed.feature.MainActivity", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("test demo beforeHookedMethod");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Class<?> c = lpparam.classLoader.loadClass("com.fq.demo.xposed.feature.MainActivity");
                    Field f = c.getDeclaredField("textView");
                    f.setAccessible(true);
                    XposedBridge.log("test demo afterHookedMethod");
                    TextView textView = (TextView) f.get(param.thisObject);
                    textView.setText("xposed after");
                }

            });
        }
    }
}
