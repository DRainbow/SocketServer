package com.sugar.android.socketserver.driver;

import android.util.Log;

import com.mwee.android.drivenbus.Driver;
import com.mwee.android.drivenbus.component.DrivenMethod;
import com.sugar.android.socket.businessmodel.User;
import com.sugar.android.socket.constant.Constant;
import com.sugar.android.socket.model.Response;


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
    public Response save() {
        Log.i(TAG, "UserDriver, save use");
        Response res = new Response();
        res.code = Constant.SUCCESS;
        res.message = "success";
        res.data = "use/save success";
        return res;
    }

    @DrivenMethod(uri = TAG + "/delete")
    public Response delete(User user) {
        Log.i(TAG, "UserDriver, delete user " + user.getName());
        Response res = new Response();
        res.code = Constant.SUCCESS;
        res.message = "success";
        res.data = "use/delete success";
        return res;
    }
}
