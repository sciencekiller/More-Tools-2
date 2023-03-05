//More Messages窗口
package com.mt.more_tools;

//导入

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

import com.melloware.jintellitype.*;

import static com.mt.more_tools.Main.*;

public class MMPWindow {
    private static final int GLOBAL_HOT_KEY_ESC = 0;

    //模拟键盘粘贴函数(By ChatGPT)
    public static void Press_Ctrl_And_V() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (Exception e) {
            WriteLog(e, "Failed to create robot", "mmp");
            return;
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Main.WriteLog("Ctrl_And_V pressed", "INFO", "mmp");
    }

    //模拟键盘按下Enter(By ChatGPT)
    public static void Press_Enter() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (Exception e) {
            WriteLog(e, "Failed to create robot", "mmp");
            return;
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        WriteLog("Enter pressed", "INFO", "mmp");
    }

    //模拟键盘按下Ctrl+Enter(By ChatGPT)
    public static void Press_Ctrl_And_Enter() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (Exception e) {
            WriteLog(e, "Failed to create robot", "mmp");
            return;
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ENTER);
        WriteLog("Ctrl_Enter pressed", "INFO", "mmp");
    }

    //模拟键盘按下空格(By ChatGPT)
    public static void Press_Space() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            WriteLog(e, "Failed to create robot", "mmp");
            return;
        }
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        WriteLog("Space pressed", "INFO", "mmp");
    }

    //写入字符串到剪切板函数(By ChatGPT)
    public static void writeStringToClipboard(String str) {
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        WriteLog("Write " + str + " to clipboard", "INFO", "mmp");
    }

    //定义变量
    static String WordInputVariable = "";//文本输入变量
    static String NumberInputVariable = "";//次数输入变量
    static String SpacingInputVariable = "";//间隔输入变量

    //生成窗口More Messages
    public static void More_Messages() {
        WriteLog("Start more messages window", "INFO", "mmp");
        //定义Stage
        Stage MMPStage = new Stage();

        //定义GridPane
        GridPane pane = new GridPane();
        pane.setVgap(15);
        pane.setHgap(15);

        //定义标签
        Label WordLabel = new Label("请输入文本:     ");//文本
        WordLabel.setFont(Font.font("Kaiti", 14));
        Label NumberLabel = new Label("请输入次数:     ");//次数
        NumberLabel.setFont(Font.font("Kaiti", 14));
        Label WordModeLabel = new Label("请选择文本模式:");//文本模式
        WordModeLabel.setFont(Font.font("Kaiti", 14));
        Label SendModeLabel = new Label("请选择发送模式:");//发送方式
        SendModeLabel.setFont(Font.font("Kaiti", 14));
        Label SoftWareLabel = new Label("请选择您的软件:");//软件
        SoftWareLabel.setFont(Font.font("Kaiti", 14));
        Label SpacingLabel = new Label("请输入间隔时间:");//间隔
        SpacingLabel.setFont(Font.font("Kaiti", 14));

        //定义文本输入框
        TextField WordInput = new TextField("Welcome To More Messages 2.0~");
        WordInput.setPrefWidth(400);

        //定义次数输入框
        TextField NumberInput = new TextField("100");
        NumberInput.setPrefWidth(400);

        //定义间隔输入框
        TextField SpacingInput = new TextField("0.1");
        SpacingInput.setPrefWidth(400);

        //定义文字模式下拉框
        ComboBox<String> WordModeComboBox = new ComboBox<>();//定义
        WordModeComboBox.getItems().addAll("文本框模式", "剪切板模式");//定义子成员
        WordModeComboBox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        WordModeComboBox.setEditable(false);//设置为不可编辑
        WordModeComboBox.setValue("文本框模式");//设置默认值
        WordModeComboBox.setPrefWidth(400);//设置宽度400

        //定义发送方式下拉框
        ComboBox<String> SendModeComboBox = new ComboBox<>();//定义
        SendModeComboBox.getItems().addAll("直列式", "波浪式", "随机式");//定义子成员
        SendModeComboBox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        SendModeComboBox.setEditable(false);//设置为不可编辑
        SendModeComboBox.setValue("直列式");//设置默认值
        SendModeComboBox.setPrefWidth(400);//设置宽度400

        //定义软件下拉框
        ComboBox<String> SoftwareComboBox = new ComboBox<>();//定义
        SoftwareComboBox.getItems().addAll("微信", "QQ", "钉钉", "其他按Enter发送的软件", "其他按Ctrl+Enter发送的软件");//定义子成员
        SoftwareComboBox.setPlaceholder(new Label("这里空空如也"));//为空时显示
        SoftwareComboBox.setEditable(false);//设置不可编辑
        SoftwareComboBox.setValue("其他按Enter发送的软件");//设置默认值
        SoftwareComboBox.setPrefWidth(400);//设置宽度400

        //定义按钮
        Button ExitButton = new Button("退出");//退出按钮
        ExitButton.setPrefWidth(100);//设置宽度100
        ExitButton.setBackground(bg);
        Button StartButton = new Button("开始");//开始按钮
        StartButton.setPrefWidth(100);//设置宽度100
        StartButton.setBackground(bg);
        Button AboutButton = new Button("关于");//关于按钮
        AboutButton.setPrefWidth(100);//设置宽度100
        AboutButton.setBackground(bg);

        //添加组件
        pane.add(WordLabel, 0, 0);
        pane.add(WordInput, 1, 0);
        pane.add(NumberLabel, 0, 1);
        pane.add(NumberInput, 1, 1);
        pane.add(WordModeLabel, 0, 2);
        pane.add(WordModeComboBox, 1, 2);
        pane.add(SendModeLabel, 0, 3);
        pane.add(SendModeComboBox, 1, 3);
        pane.add(SoftWareLabel, 0, 4);
        pane.add(SoftwareComboBox, 1, 4);
        pane.add(SpacingLabel, 0, 5);
        pane.add(SpacingInput, 1, 5);
        pane.add(StartButton, 1, 6);
        pane.add(AboutButton, 1, 7);
        pane.add(ExitButton, 1, 8);

        //创建开始按钮行动
        StartButton.setOnAction(actionEvent -> {
            NumberInputVariable = NumberInput.getText();
            if (Objects.equals(NumberInputVariable, "")) {
                Alerts.NoNumberInputAlert();
                return;
            }
            int Times = 0;
            try {
                Times = Integer.parseInt(NumberInputVariable);
            } catch (Exception e) {
                Alerts.InputErrorAlert();
                e.printStackTrace();
            }
            SpacingInputVariable = SpacingInput.getText();
            if (Objects.equals(SpacingInputVariable, "")) {
                Alerts.NoSpacingInputAlert();
                return;
            }
            float Spacing = 0;
            try {
                Spacing = Float.parseFloat(SpacingInputVariable);
            } catch (Exception e) {
                Alerts.InputErrorAlert();
                e.printStackTrace();
            }
            boolean WordMode;
            WordMode = Objects.equals(WordModeComboBox.getValue(), "文本框模式");
            int SendMode;
            if (Objects.equals(SendModeComboBox.getValue(), "直列式")) {
                SendMode = 1;
            } else if (Objects.equals(SendModeComboBox.getValue(), "波浪式")) {
                SendMode = 2;
            } else {
                SendMode = 3;
            }

            //"微信", "QQ", "钉钉", "其他按Enter发送的软件", "其他按Ctrl+Enter发送的软件"
            int Software;
            if (Objects.equals(SoftwareComboBox.getValue(), "微信") || Objects.equals(SoftwareComboBox.getValue(), "QQ") || Objects.equals(SoftwareComboBox.getValue(), "钉钉") || Objects.equals(SoftwareComboBox.getValue(), "其他按Enter发送的软件")) {
                Software = 1;
            } else {
                Software = 2;
            }
            WordInputVariable = WordInput.getText();
            if (Objects.equals(WordInputVariable, "") && WordMode) {
                Alerts.NoMessageInputAlert();
                return;
            }
            SendThread NewSendThread = new SendThread(WordInputVariable, Times, Spacing, WordMode, SendMode, Software);
            NewSendThread.start();
        });

        //创建退出按钮行动
        ExitButton.setOnAction(actionEvent -> {
            WriteLog("Exit more messages", "INFO", "mmp");
            MMPStage.close();
        });

        //创建关于按钮行动
        AboutButton.setOnAction(actionEvent -> {
            WriteLog("Show about alert", "INFO", "mmp");
            Alerts.MMPAboutAlert();
        });

        //配置场景
        int img;
        Random random = new Random();
        img = random.nextInt(11);
        img++;
        Image i = new Image(TempFolder + "/MT/Temp/background-" + img + ".jpg");
        WriteLog("Get background image: background-" + img + ".jpg", "INFO", "mmp");
        BackgroundImage bgi = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);
        pane.setBackground(bg);
        WriteLog("Create more message scene", "INFO", "mmp");
        Scene MMPScene = new Scene(pane, 600, 400);//定义场景
        WriteLog("Get more message stylesheet", "INFO", "mmp");
        URL url_css = Main.class.getClassLoader().getResource("Style.css");
        assert url_css != null;
        MMPScene.getStylesheets().add(url_css.toExternalForm());
        MMPStage.setScene(MMPScene);//设置场景
        MMPStage.setResizable(false);//设置不可调整大小
        MMPStage.setTitle("More Messages");//设置标题
        WriteLog("Get more message icon", "INFO", "mmp");
        MMPStage.getIcons().add(new Image(TempFolder + "/MT/Temp/MMP-ICON.jpg"));
        WriteLog("Show more message window", "INFO", "mmp");
        MMPStage.show();//显示
    }

    //打断线程函数
    public static void StopSend() {
        WriteLog("Ready to interrupt send", "WARNING", "mmp");
        ThreadGroup CurrentGroup = Thread.currentThread().getThreadGroup();
        WriteLog("Get threads", "INFO", "mmp");
        int ThreadNum = CurrentGroup.activeCount();
        Thread[] NowThreads = new Thread[ThreadNum];
        CurrentGroup.enumerate(NowThreads);
        for (int i = 0; i < ThreadNum; i++) {
            String name = NowThreads[i].getName();
            WriteLog("Find thread:" + name, "INFO", "mmp");
            if (name.equals("SendThread")) {
                WriteLog("Find Send Thread", "INFO", "mmp");
                NowThreads[i].interrupt();
                WriteLog("Interrupted Send Thread", "SUCCESS", "mmp");
            }
        }
    }

    //发送函数
    public static void StartSend(String Messages, int Times, float Spacing, boolean WordMode, int SendMode, int Software) {
        WriteLog("Ready to send", "INFO", "mmp");
        if (Times == 0 || Spacing == 0) {
            WriteLog("Find error value,return the method", "ERROR", "mmp");
            return;
        }
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY_ESC, 0, 27);
        WriteLog("Register key ESC,value=" + GLOBAL_HOT_KEY_ESC, "INFO", "mmp");
        JIntellitype.getInstance().addHotKeyListener(j -> StopSend());
        synchronized (Thread.currentThread()) {
            try {
                WriteLog("Sleep 1s", "INFO", "mmp");
                Thread.currentThread().notifyAll();
                Thread.currentThread().wait(1000);//休眠1秒
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                WriteLog(e, "Failed to sleep", "mmp");
            }
        }
        if (WordMode) {
            //复制消息
            WriteLog("Copy message", "INFO", "mmp");
            writeStringToClipboard(Messages);
        }
        int Space = 0;
        boolean Up_Or_Down = false;//false 上升
        if (SendMode == 2 || SendMode == 3) {
            Spacing += 5000;
        }
        for (int i = 0; i < Times && !Thread.currentThread().isInterrupted(); i++) {
            WriteLog("Send number:" + i, "INFO", "mmp");
            if (SendMode == 2) {
                for (int j = 0; j < Space; j++) {
                    Press_Space();
                }
                if (Space == 20) Up_Or_Down = true;
                if (Space == 0) Up_Or_Down = false;
                if (Up_Or_Down) {
                    Space -= 2;
                } else {
                    Space += 2;
                }
            }
            if (SendMode == 3) {
                Random random = new Random();
                Space = random.nextInt(20);
                WriteLog("Get random number:" + Space, "INFO", "mmp");
                for (int j = 0; j < Space; j++) {
                    Press_Space();
                }
            }
            //粘贴
            Press_Ctrl_And_V();
            if (Software == 1) {
                Press_Enter();//按Enter
            } else {
                Press_Ctrl_And_Enter();//按Ctrl+Enter
            }
            synchronized (Thread.currentThread()) {
                try {

                    Thread.currentThread().notifyAll();
                    Thread.currentThread().wait((long) (Spacing * 1000));
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    WriteLog(e, "Failed to sleep", "mmp");
                }
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            WriteLog("Send over without interrupt", "SUCCESS", "mmp");
            //正常结束
            Platform.runLater(Alerts::SendOver);
        } else {
            WriteLog("Send over but interrupted", "FAILED", "mmp");
            Platform.runLater(Alerts::SendInterrupted);
        }
        JIntellitype.getInstance().unregisterHotKey(GLOBAL_HOT_KEY_ESC);
        WriteLog("Unregister key ESC:" + GLOBAL_HOT_KEY_ESC, "INFO", "mmp");
    }

    //定义发送线程，保证UI线程不会堵塞
    static class SendThread extends Thread {
        //定义变量
        String Messages;
        int Times;
        float Spacing;
        boolean WordMode;
        int SendMode;
        int Software;

        //构建方法
        public SendThread(String Messages, int Times, float Spacing, boolean WordMode, int SendMode, int Software) {
            super("SendThread");
            WriteLog("Created SendThread", "SUCCESS", "mmp");
            //复制传参
            this.Messages = Messages;
            WriteLog("Message:" + Messages, "INFO", "mmp");
            this.Times = Times;
            WriteLog("Times:" + Times, "INFO", "mmp");
            this.Spacing = Spacing;
            WriteLog("Spacing:" + Spacing, "INFO", "mmp");
            this.WordMode = WordMode;
            WriteLog("WordMode:" + WordMode, "INFO", "mmp");
            this.SendMode = SendMode;
            WriteLog("SendMode:" + SendMode, "INFO", "mmp");
            this.Software = Software;
            WriteLog("Software:" + Software, "INFO", "mmp");
        }

        //重写run启动方法
        @Override
        public void run() {
            WriteLog("Send thread started", "SUCCESS", "MMP");
            StartSend(WordInputVariable, Times, Spacing, WordMode, SendMode, Software);
        }
    }
}
