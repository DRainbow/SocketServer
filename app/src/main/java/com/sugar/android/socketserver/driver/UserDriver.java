package com.sugar.android.socketserver.driver;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.pos.localsocketserver.driverbus.Driver;
import com.android.pos.localsocketserver.driverbus.component.DrivenMethod;

/**
 * @ClassName: UserDriver
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午4:34
 */
public class UserDriver extends Driver {

    private static final String TAG = "user";

    @Override
    public String getModuleName() {
        return TAG;
    }

    @DrivenMethod(uri = TAG + "/save")
    public void save(final Context context) {
        Log.i(TAG, "UserDriver, save use");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "save user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @DrivenMethod(uri = TAG + "/delete")
    public void delete(String param) {
        Log.i(TAG, "UserDriver, delete user " + param);
    }
}
