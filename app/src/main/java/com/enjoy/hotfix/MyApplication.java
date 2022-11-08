package com.enjoy.hotfix;

import android.app.Application;
import android.content.Context;

import com.enjoy.patch.EnjoyFix;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //
        EnjoyFix.installPatch(this,"/sdcard/patch.jar");
    }
}
