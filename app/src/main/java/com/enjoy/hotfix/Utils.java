package com.enjoy.hotfix;


import android.util.Log;

public class Utils {

    private static final String TAG = "Utils";
    public static void test() {
//        Log.e(TAG,"执行test");
       throw new IllegalStateException("出错啦！！！");
    }
}
