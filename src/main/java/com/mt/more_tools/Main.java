//TODO 主类
package com.mt.more_tools;

//TODO 导入

import javafx.application.Application;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson2.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class Main {
    //TODO 定义变量
    public static Background bg;
    public static String MainVersion;//More Tools版本
    public static String MMPVersion;//More Messages Part版本

    static FileWriter writer = null;

    public static void WriteLog(Exception error, String log, String from) {
        String level = "ERROR";
        log = log + "\n" + ExceptionUtils.getStackTrace(error);
        LogWriter(log, level, from);
    }

    public static void WriteLog(Exception error, String log) {
        log = log + "\n" + ExceptionUtils.getStackTrace(error);
        String level = "ERROR";
        String from = "main";
        LogWriter(log, level, from);
    }

    public static void WriteLog(String log, String level) {
        String from = "main";
        LogWriter(log, level, from);
    }

    public static void WriteLog(String log, String level, String from) {
        LogWriter(log, level, from);
    }

    public static void LogWriter(String log, String level, String from) {
        Date date = new Date();
        SimpleDateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String Time = Formatter.format(date);
        log = Time + " [" + level + "] [" + from + "] " + log + "\n";
        try {
            writer.append(log);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO 主方法启动主窗口
    public static void main(String[] args) throws IOException {

        //TODO 创建日志文件
        File temp = null;
        try {
            temp = File.createTempFile("temp-file-name", ".tmp");
        } catch (Exception e) {
            WriteLog(e, "Failed to find temp dictionary");
        }
        assert temp != null;
        String AbsolutePath = temp.getAbsolutePath();
        String TempFolder = AbsolutePath.substring(0, AbsolutePath.lastIndexOf(File.separator));
        Date date = new Date();
        SimpleDateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
        String Time = Formatter.format(date);
        File dir = new File(TempFolder + "/MT/Logs");
        boolean mkdir = false;
        if (!dir.exists()) {
            mkdir = dir.mkdirs();
        }
        File LoadFile = new File(TempFolder + "/MT/Logs/Log-" + Time + ".log");
        boolean mkfil = false;
        try {
            if (!LoadFile.exists()) {
                mkfil = LoadFile.createNewFile();
            }
            writer = new FileWriter(LoadFile, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mkdir) WriteLog("Make dictionary successfully", "SUCCESS");
        else WriteLog("Dictionary exists", "INFO");
        if (mkfil) WriteLog("Make file successfully", "SUCCESS");
        else WriteLog("File exists", "INFO");

        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length() == 1 ? "0" + red : red;
        //判断绿色代码的位数
        green = green.length() == 1 ? "0" + green : green;
        //判断蓝色代码的位数
        blue = blue.length() == 1 ? "0" + blue : blue;
        String color = "#" + red + green + blue;
        WriteLog("Get button color:" + color, "INFO");
        BackgroundFill bgf = new BackgroundFill(Paint.valueOf(color), null, null);
        bg = new Background(bgf);

        WriteLog("Reading Config.json", "INFO");
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
        WriteLog("Main Version:" + MainVersion, "INFO");
        WriteLog("MMP Version:" + MMPVersion, "INFO");

        //TODO 启动主窗口
        Application.launch(MainWindow.class, args);
        writer.close();
    }
}
