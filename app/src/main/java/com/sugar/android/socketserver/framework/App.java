package com.sugar.android.socketserver.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.pos.localsocketserver.R;
import com.mwee.android.drivenbus.DrivenBusManager;
import com.mwee.android.drivenbus.exception.DrivenException;
import com.sugar.android.socketserver.socket.SocketServer;

import java.util.Stack;

/**
 * @ClassName: App
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午4:48
 */
public class App extends Application {

    public static final String TAG = App.class.getSimpleName();

    public static Context sContext;

    public static Stack<Activity> sActivityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        try {
            DrivenBusManager.getInstance().init(getResources().getStringArray(R.array.drive_path));
        } catch (DrivenException e) {
            e.printStackTrace();
        }

        SocketServer.getInstance().init();

    }

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        Activity activity = sActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishActivity() {
        Activity activity = sActivityStack.lastElement();
        finishActivity(activity);
    }
}
