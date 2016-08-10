package com.sugar.android.socketserver.driver;

import android.content.Context;
import android.util.Log;

import com.mwee.android.drivenbus.Driver;
import com.mwee.android.drivenbus.component.DrivenMethod;
import com.sugar.android.socket.model.Response;


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
    public Response execute(final Context context) {
        Log.i(TAG, "EventDriver, execute event");
        Response res = new Response();
        res.code = 200;
        res.message = "success";
        return res;
    }
}
