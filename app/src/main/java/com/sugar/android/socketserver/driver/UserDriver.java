package com.sugar.android.socketserver.driver;

import android.content.Context;
import android.util.Log;

import com.mwee.android.drivenbus.Driver;
import com.mwee.android.drivenbus.component.DrivenMethod;
import com.sugar.android.socket.model.Response;
import com.sugar.android.socket.model.User;


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
    public Response save(final Context context) {
        Log.i(TAG, "UserDriver, save use");
        Response res = new Response();
        res.code = 200;
        res.message = "success";
        res.data = "use/save success";
        return res;
    }

    @DrivenMethod(uri = TAG + "/delete")
    public Response delete(Context context, User user) {
        Log.i(TAG, "UserDriver, delete user " + user.getName());
        Response res = new Response();
        res.code = 200;
        res.message = "success";
        res.data = "use/delete success";
        return res;
    }
}
