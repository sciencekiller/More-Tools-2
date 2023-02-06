//TODO 主类
package com.mmp.more_tools;

//TODO 导入

import javafx.application.Application;

import java.io.*;
import java.nio.charset.StandardCharsets;

import com.alibaba.fastjson2.*;

public class Main {
    //TODO 定义变量
    public static String MainVersion;//More Tools版本
    public static String MMPVersion;//More Messages Part版本

    //TODO 主方法启动主窗口
    public static void main(String[] args) {
        //TODO 从JSON读取版本号
        try {
            String StringConfig;
            String path = "Config.json";
            InputStream ConfigStream = Main.class.getClassLoader().getResourceAsStream(path);
            assert ConfigStream != null;
            BufferedReader ConfigBufferedReader = new BufferedReader(new InputStreamReader(ConfigStream, StandardCharsets.UTF_8));
            StringBuilder ConfigStringBuilder = new StringBuilder();
            String ConfigLine;
            while ((ConfigLine = ConfigBufferedReader.readLine()) != null) {
                ConfigStringBuilder.append(ConfigLine);
            }
            StringConfig = ConfigStringBuilder.toString();
            JSONObject Config = JSON.parseObject(StringConfig);
            assert Config != null;
            MainVersion = (String) Config.get("Version");
            MMPVersion = (String) Config.get("MMPVersion");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO 启动主窗口
        Application.launch(MainWindow.class, args);
    }
}
