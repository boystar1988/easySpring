package com.jianzhong.demo.constant;

import java.util.HashMap;
import java.util.Map;

public class AuthConstant
{
    public static final String AUTH_HEADER_PREFIX_TOKEN    = "";
    public static final String AUTH_REDIS_FIELD_TOKEN      = "es:token:";
    public static final String AUTH_REDIS_FIELD_USER_TOKEN = "es:user:";

    public static final String AUTH_HEADER_FIELD_TOKEN     = "x-auth-token";
    public static final String AUTH_HEADER_FIELD_DEVICE    = "x-auth-device";
    public static final String AUTH_HEADER_FIELD_VERSION   = "ver";
    public static final String AUTH_HEADER_FIELD_UUID      = "uuid";

    public static final String AUTH_DEVICE_PC       = "1";
    public static final String AUTH_DEVICE_ANDROID  = "2";
    public static final String AUTH_DEVICE_IOS      = "3";
    public static final String AUTH_DEVICE_H5       = "4";
    public static final String AUTH_DEVICE_MINIPROG = "6";

    public static final Map<String,String> deviceMap = new HashMap<>();
    static {
        deviceMap.put(AUTH_DEVICE_PC,"PC");
        deviceMap.put(AUTH_DEVICE_ANDROID,"安卓");
        deviceMap.put(AUTH_DEVICE_IOS,"苹果");
        deviceMap.put(AUTH_DEVICE_H5,"H5");
        deviceMap.put(AUTH_DEVICE_MINIPROG,"小程序");
    };
}