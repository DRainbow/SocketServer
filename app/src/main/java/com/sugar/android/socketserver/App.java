package com.sugar.android.socketserver;

import android.app.Application;

import com.mwee.android.drivenbus.DrivenBusManager;
import com.mwee.android.drivenbus.exception.DrivenException;
import com.sugar.android.socketserver.socket.SocketServer;

/**
 * @ClassName: App
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午4:48
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        try {
            DrivenBusManager.getInstance().init(new String[]{
                    "com.sugar.android.socketserver.driver.EventDriver",
                    "com.sugar.android.socketserver.driver.UserDriver"
            });
            DrivenBusManager.getInstance().broadcast("init", this);
        } catch (DrivenException e) {
            e.printStackTrace();
        }

        SocketServer.getInstance().init();
    }

    public static App getInstance() {
        return instance;
    }
}
