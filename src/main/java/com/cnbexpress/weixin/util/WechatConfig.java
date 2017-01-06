package com.cnbexpress.weixin.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WechatConfig {
    public static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechatconfig.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}