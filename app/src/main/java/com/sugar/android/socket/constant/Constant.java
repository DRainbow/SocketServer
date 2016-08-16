package com.sugar.android.socket.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName: Constant
 * @Description:
 * @author: SugarT
 * @date: 16/8/10 下午6:20
 */
public class Constant {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    public static final int FORBIDDEN = 403;

    @IntDef({SUCCESS, ERROR, FORBIDDEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResponseCode {
    }

    public static final String HEADER_API_VERSION = "api_version";
}
