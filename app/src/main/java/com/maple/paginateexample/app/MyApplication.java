package com.maple.paginateexample.app;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import es.dmoral.toasty.Toasty;

/**
 * author: gaogq
 * time: 2019/3/21 23:30
 * description:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toasty.Config.getInstance().apply();
        Utils.init(this);
    }
}
