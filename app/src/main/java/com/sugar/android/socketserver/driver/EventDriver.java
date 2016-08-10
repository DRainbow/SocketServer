package com.sugar.android.socketserver.driver;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.mwee.android.drivenbus.Driver;
import com.mwee.android.drivenbus.component.DrivenMethod;


/**
 * @ClassName: EventDriver
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午5:12
 */
public class EventDriver extends Driver {

    private static final String TAG = "event";

    @Override
    public String getModuleName() {
        return TAG;
    }

    @DrivenMethod(uri = TAG + "/execute")
    public void execute(final Context context) {
        Log.i(TAG, "EventDriver, execute event");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "execute event", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
