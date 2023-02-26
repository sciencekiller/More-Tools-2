//TODO More Messages窗口
package com.mt.more_tools;

//TODO 导入

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

import com.melloware.jintellitype.*;

public class MMPWindow {
    private static final int GLOBAL_HOT_KEY_ESC = 0;

    //TODO 模拟键盘粘贴函数(By ChatGPT)
    public static void Press_Ctrl_And_V() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    //TODO 模拟键盘按下Enter(By ChatGPT)
    public static void Press_Enter() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    //TODO 模拟键盘按下Ctrl+Enter(By ChatGPT)
    public static void Press_Ctrl_And_Enter() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    //TODO 模拟键盘按下空格(By ChatGPT)
    public static void Press_Space() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }

    //TODO 写入字符串到剪切板函数(By ChatGPT)
    public static void writeStringToClipboard(String str) {
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    //TODO 定义变量
    static String WordInputVariable = "";//文本输入变量
    static String NumberInputVariable = "";//次数输入变量
    static String SpacingInputVariable = "";//间隔输入变量
    static Alert SendOver = new Alert(Alert.AlertType.INFORMATION);
    static Alert SendInterrupted = new Alert(Alert.AlertType.ERROR);
    static Alert AboutAlert = new Alert(Alert.AlertType.INFORMATION);
    static Alert NoNumberInputAlert = new Alert(Alert.AlertType.ERROR);
    static Alert NoSpacingInputAlert = new Alert(Alert.AlertType.ERROR);
    static Alert NoMessageInputAlert = new Alert(Alert.AlertType.ERROR);
    static Alert InputErrorAlert = new Alert(Alert.AlertType.ERROR);

    //TODO 生成窗口More Messages
    public static void More_Messages() {
        //TODO 定义Stage
        Stage MMPStage = new Stage();

        //TODO 定义GridPane
        GridPane pane = new GridPane();
        pane.setVgap(15);
        pane.setHgap(15);

        //TODO 定义弹窗
        AboutAlert.setTitle("关于");
        AboutAlert.setHeaderText("More Messages Version" + Main.MMPVersion);
        AboutAlert.setContentText("感谢您使用More Messages Version" + Main.MMPVersion + "\n本软件由版权属于程添宇(Sciencekill)\n!本软件为More Tools中的一个组件!\nCopyright 2023 Sciencekill");

        NoNumberInputAlert.setTitle("未输入次数");
        NoNumberInputAlert.setHeaderText("您未输入次数!");
        NoNumberInputAlert.setContentText("请检查是否输入了次数，如果是误报错，请截图反馈");

        NoSpacingInputAlert.setTitle("未输入间隔");
        NoSpacingInputAlert.setHeaderText("您未输入间隔!");
        NoSpacingInputAlert.setContentText("请检查是否输入了间隔，如果是误报错，请截图反馈");

        NoMessageInputAlert.setTitle("未输入文本");
        NoMessageInputAlert.setHeaderText("您未输入文本!");
        NoMessageInputAlert.setContentText("请检查是否输入了文本，如果是误报错，请截图反馈");

        SendInterrupted.setTitle("终止");
        SendInterrupted.setHeaderText("用户强制终止了发送");
        SendInterrupted.setContentText("已将发送强制停止!");

        SendOver.setTitle("完成!");
        SendOver.setHeaderText("发送完成!");
        SendOver.setContentText("已发送完成!");

        InputErrorAlert.setTitle("输入错误");
        InputErrorAlert.setHeaderText("您的输入有误");
        InputErrorAlert.setContentText("请仔细检查输入，如果是误报错，请截图反馈");

        //TODO 定义标签
        Label WordLabel = new Label("请输入文本:     ");//文本
        Label NumberLabel = new Label("请输入次数:     ");//次数
        Label WordModeLabel = new Label("请选择文本模式:");//文本模式
        Label SendModeLabel = new Label("请选择发送模式:");//发送方式
        Label SoftWareLabel = new Label("请选择您的软件:");//软件
        Label SpacingLabel = new Label("请输入间隔时间:");//间隔

        //TODO 定义文本输入框
        TextField WordInput = new TextField("Welcome To More Messages 2.0~");
        WordInput.setPrefWidth(500);

        //TODO 定义次数输入框
        TextField NumberInput = new TextField("100");
        NumberInput.setPrefWidth(500);

        //TODO 定义间隔输入框
        TextField SpacingInput = new TextField("0.1");
        SpacingInput.setPrefWidth(500);

        //TODO 定义文字模式下拉框
        ComboBox<String> WordModeComboBox = new ComboBox<>();//定义
        WordModeComboBox.getItems().addAll("文本框模式", "剪切板模式");//定义子成员
        WordModeComboBox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        WordModeComboBox.setEditable(false);//设置为不可编辑
        WordModeComboBox.setValue("文本框模式");//设置默认值
        WordModeComboBox.setPrefWidth(500);//设置宽度500

        //TODO 定义发送方式下拉框
        ComboBox<String> SendModeComboBox = new ComboBox<>();//定义
        SendModeComboBox.getItems().addAll("直列式", "波浪式", "随机式");//定义子成员
        SendModeComboBox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        SendModeComboBox.setEditable(false);//设置为不可编辑
        SendModeComboBox.setValue("直列式");//设置默认值
        SendModeComboBox.setPrefWidth(500);//设置宽度500

        //TODO 定义软件下拉框
        ComboBox<String> SoftwareComboBox = new ComboBox<>();//定义
        SoftwareComboBox.getItems().addAll("微信", "QQ", "钉钉", "其他按Enter发送的软件", "其他按Ctrl+Enter发送的软件");//定义子成员
        SoftwareComboBox.setPlaceholder(new Label("这里空空如也"));//为空时显示
        SoftwareComboBox.setEditable(false);//设置不可编辑
        SoftwareComboBox.setValue("其他按Enter发送的软件");//设置默认值
        SoftwareComboBox.setPrefWidth(500);//设置宽度500

        //TODO 定义按钮
        Button ExitButton = new Button("退出");//退出按钮
        ExitButton.setPrefWidth(100);//设置宽度100
        Button StartButton = new Button("开始");//开始按钮
        StartButton.setPrefWidth(100);//设置宽度100
        Button AboutButton = new Button("关于");//关于按钮
        AboutButton.setPrefWidth(100);//设置宽度100

        //TODO 添加组件
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

        //TODO 创建开始按钮行动
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

        //TODO 创建退出按钮行动
        ExitButton.setOnAction(actionEvent -> MMPStage.close());

        //TODO 创建关于按钮行动
        AboutButton.setOnAction(actionEvent -> Alerts.MMPAboutAlert());

        //TODO 配置场景
        Scene MMPScene = new Scene(pane, 600, 400);//定义场景
        URL url_css = Main.class.getClassLoader().getResource("Style.css");
        assert url_css != null;
        MMPScene.getStylesheets().add(url_css.toExternalForm());
        MMPStage.setScene(MMPScene);//设置场景
        MMPStage.setResizable(false);//设置不可调整大小
        MMPStage.setTitle("More Messages Ver2.0.0");//设置标题
        MMPStage.getIcons().add(new Image("file:src/main/resources/images/MMP-ICON.jpg"));
        MMPStage.show();//显示
    }

    //TODO 打断线程函数
    public static void StopSend() {
        ThreadGroup CurrentGroup = Thread.currentThread().getThreadGroup();
        int ThreadNum = CurrentGroup.activeCount();
        Thread[] NowThreads = new Thread[ThreadNum];
        CurrentGroup.enumerate(NowThreads);
        for (int i = 0; i < ThreadNum; i++) {
            String name = NowThreads[i].getName();
            if (name.equals("SendThread")) {
                NowThreads[i].interrupt();
            }
        }
    }

    //TODO 发送函数
    public static void StartSend(String Messages, int Times, float Spacing, boolean WordMode, int SendMode, int Software) {
        if (Times == 0 || Spacing == 0) {
            return;
        }
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY_ESC, 0, 27);
        JIntellitype.getInstance().addHotKeyListener(j -> StopSend());
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().notifyAll();
                Thread.currentThread().wait(1000);//休眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (WordMode) {
            //复制消息
            writeStringToClipboard(Messages);
        }
        int Space = 0;
        boolean Up_Or_Down = false;//false 上升
        if (SendMode == 2 || SendMode == 3) {
            Spacing += 5000;
        }
        for (int i = 0; i < Times && !Thread.currentThread().isInterrupted(); i++) {
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
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            //正常结束
            Platform.runLater(Alerts::SendOver);
        } else {
            Platform.runLater(Alerts::SendInterrupted);
        }
        JIntellitype.getInstance().unregisterHotKey(GLOBAL_HOT_KEY_ESC);
    }

    //TODO 定义发送线程，保证UI线程不会堵塞
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
            //复制传参
            this.Messages = Messages;
            this.Times = Times;
            this.Spacing = Spacing;
            this.WordMode = WordMode;
            this.SendMode = SendMode;
            this.Software = Software;
        }

        //重写run启动方法
        @Override
        public void run() {
            StartSend(WordInputVariable, Times, Spacing, WordMode, SendMode, Software);
        }
    }
}