//主类
package com.mt.more_tools;

//导入

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
    //定义变量
    public static Background bg;
    public static String MainVersion;//More Tools版本
    public static String MMPVersion;//More Messages Part版本

    static FileWriter writer = null;
    public static String TempFolder;

    public static String mmp_Messages;
    public static int mmp_Times;
    public static float mmp_Spacing;
    public static boolean mmp_WordMode;
    public static int mmp_SendMode;
    public static int mmp_Software;

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

    public static void ReadImageFromStream(InputStream inStream, String name) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024 * 512];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        byte[] data = outStream.toByteArray();
        File imagefile = new File(TempFolder + "/MT/Temp/" + name + ".jpg");
        FileOutputStream fos = new FileOutputStream(imagefile);
        fos.write(data);
        inStream.close();
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

    private static final String SPACE = "   ";

    public static String formatJson(String json) {
        StringBuilder result = new StringBuilder();

        int length = json.length();
        int number = 0;
        char key;

        // 遍历输入字符串。
        for (int i = 0; i < length; i++) {
            // 1、获取当前字符。
            key = json.charAt(i);

            // 2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                // （1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                // （2）打印：当前字符。
                result.append(key);

                // （3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                // （4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                // （5）进行下一次循环。
                continue;
            }

            // 3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                // （1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                // （2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                // （3）打印：当前字符。
                result.append(key);

                // （4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                // （5）继续下一次循环。
                continue;
            }

            // 4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            // 5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    private static String indent(int number) {
        return SPACE.repeat(Math.max(0, number));
    }

    public static String readJsonFile(String fileName) {
        String jsonStr;
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //主方法启动主窗口
    public static void main(String[] args) throws IOException {

        //创建日志文件
        File temp = null;
        try {
            temp = File.createTempFile("temp-file-name", ".tmp");
        } catch (Exception e) {
            WriteLog(e, "Failed to find temp dictionary");
        }
        assert temp != null;
        String AbsolutePath = temp.getAbsolutePath();
        TempFolder = AbsolutePath.substring(0, AbsolutePath.lastIndexOf(File.separator));
        Date date = new Date();
        SimpleDateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
        String Time = Formatter.format(date);
        File dir = new File(TempFolder + "/MT/Logs");
        boolean mkdir = false;
        boolean mktmp = false;
        if (!dir.exists()) {
            mkdir = dir.mkdirs();
        }
        File tp = new File(TempFolder + "/MT/Temp");
        if (!tp.exists()) {
            mktmp = tp.mkdirs();
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
        if (mkdir && mktmp) WriteLog("Make dictionary successfully", "SUCCESS");
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
        //从JSON读取版本号
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

        for (int i = 1; i <= 11; i++) {
            String Temp_Path = TempFolder + "/MT/Temp/background-" + i + ".jpg";
            String path = "background-" + i + ".jpg";
            File imgfile = new File(Temp_Path);
            if (!imgfile.exists()) {
                InputStream is = Main.class.getClassLoader().getResourceAsStream(path);
                assert is != null;
                ReadImageFromStream(is, "background-" + i);
                is.close();
            }
        }
        File MMP_Icon = new File(TempFolder + "/MT/Temp/MMP-ICON.jpg");
        if (!MMP_Icon.exists()) {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("MMP-ICON.jpg");
            assert is != null;
            ReadImageFromStream(is, "MMP-ICON");
            is.close();
        }
        File MT_Icon = new File(TempFolder + "/MT/Temp/MT-ICON.jpg");
        if (!MT_Icon.exists()) {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("MT-ICON.jpg");
            assert is != null;
            ReadImageFromStream(is, "MT-ICON");
            is.close();
        }
        File MCP_Icon = new File(TempFolder + "/MT/Temp/MCP-ICON.jpg");
        if (!MCP_Icon.exists()) {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("MCP-ICON.jpg");
            assert is != null;
            ReadImageFromStream(is, "MCP-ICON");
            is.close();
        }
        File Configs = new File(TempFolder + "/MT/Configs.json");
        if (!Configs.exists()) {
            //String Messages, int Times, float Spacing, boolean WordMode, int SendMode, int Software
            JSONObject config = new JSONObject();
            JSONObject mmp_config = new JSONObject();
            mmp_config.put("Messages", "Welcome To More Messages 2.0~");
            mmp_Messages = "Welcome To More Messages 2.0~";
            mmp_config.put("Times", 100);
            mmp_Times = 100;
            mmp_config.put("Spacing", 0.1);
            mmp_Spacing = 0.1f;
            mmp_config.put("WordMode", true);
            mmp_WordMode = true;
            mmp_config.put("SendMode", 1);
            mmp_SendMode = 1;
            mmp_config.put("Software", 1);
            mmp_Software = 1;
            config.put("mmp", mmp_config);
            String jsonString = formatJson(config.toString());
            Writer write = new OutputStreamWriter(new FileOutputStream(Configs), StandardCharsets.UTF_8);
            write.write(jsonString);
            write.flush();
            write.close();
        } else {
            String path = TempFolder + "/MT/Configs.json";
            String s = readJsonFile(path);
            JSONObject config = JSON.parseObject(s);
            assert config != null;
            JSONObject mmp_config = config.getJSONObject("mmp");
            mmp_Messages = mmp_config.getString("Messages");
            mmp_Times = mmp_config.getIntValue("Times");
            mmp_Spacing = mmp_config.getFloatValue("Spacing");
            mmp_WordMode = mmp_config.getBooleanValue("WordMode");
            mmp_SendMode = mmp_config.getIntValue("SendMode");
            mmp_Software = mmp_config.getIntValue("Software");
        }
        //启动主窗口
        Application.launch(MainWindow.class, args);
        writer.close();
    }
}
