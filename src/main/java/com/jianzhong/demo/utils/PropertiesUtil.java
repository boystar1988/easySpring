package com.jianzhong.demo.utils;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import java.util.Properties;

/**
 * Created by Tiger on 2018/10/10.
 * 读取公共apollo配置
 */
public class PropertiesUtil {
    private static final String COMMON = "easySpring.common";
    public static Properties properties = new Properties();
    static {
        Config commonConfig = ConfigService.getConfig(COMMON);
        if(commonConfig != null){
            for(String key : commonConfig.getPropertyNames()){
                properties.setProperty(key,commonConfig.getProperty(key,null));
            }
        }
    }
}