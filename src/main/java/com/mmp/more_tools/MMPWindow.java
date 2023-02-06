//TODO More Messages窗口
package com.mmp.more_tools;

//TODO 导入

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MMPWindow {
    //TODO 定义变量
    static String WordInputVariable = "";//文本输入变量
    static String NumberInputVariable = "";//次数输入变量
    static String SpacingInputVariable = "";//间隔输入变量

    //TODO 生成窗口More Messages
    public static void More_Messages() {
        //TODO 定义Stage
        Stage MMPStage = new Stage();

        //TODO 定义GridPane
        GridPane pane = new GridPane();
        pane.setVgap(15);
        pane.setHgap(15);

        //TODO 定义弹窗
        Alert AboutAlert = new Alert(Alert.AlertType.INFORMATION);
        AboutAlert.setTitle("关于");
        AboutAlert.setHeaderText("More Messages Version" + Main.MMPVersion);
        AboutAlert.setContentText("感谢您使用More Messages Version" + Main.MMPVersion + "\n本软件由版权属于程添宇(Sciencekill)\n!本软件为More Tools中的一个组件!\nCopyright 2023 Sciencekill");

        //TODO 定义标签
        Label TextLabel = new Label("请输入文本:     ");//文本
        Label NumberLabel = new Label("请输入次数:     ");//次数
        Label WordModeLabel = new Label("请选择文本模式:");//文本模式
        Label SendModeLabel = new Label("请选择发送模式:");//发送方式
        Label SoftWareLabel = new Label("请选择您的软件:");//软件
        Label SpacingLabel = new Label("请输入间隔时间(s):");//间隔

        //TODO 定义文本输入框
        TextField TextEntry = new TextField();
        TextEntry.setText(WordInputVariable);
        TextEntry.setPrefWidth(500);

        //TODO 定义次数输入框
        TextField NumberInput = new TextField();
        NumberInput.setText(NumberInputVariable);
        NumberInput.setPrefWidth(500);

        //TODO 定义间隔输入框
        TextField SpacingInput = new TextField();
        SpacingInput.setText(SpacingInputVariable);
        SpacingInput.setPrefWidth(500);

        //TODO 定义文字模式下拉框
        ComboBox<String> WordModeComboBox = new ComboBox<>();//定义
        WordModeComboBox.getItems().addAll("文本框模式", "剪切板模式");//定义子成员
        WordModeComboBox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        WordModeComboBox.setEditable(false);//设置为不可编辑
        WordModeComboBox.setValue("文本模式");//设置默认值
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
        pane.add(TextLabel, 0, 0);
        pane.add(TextEntry, 1, 0);
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

        });

        //TODO 创建退出按钮行动
        ExitButton.setOnAction(actionEvent -> MMPStage.close());

        //TODO 创建关于按钮行动
        AboutButton.setOnAction(actionEvent -> AboutAlert.showAndWait());

        //TODO 配置场景
        Scene MMPScene = new Scene(pane, 600, 400);//定义场景
        MMPStage.setScene(MMPScene);//设置场景
        MMPStage.setResizable(false);//设置不可调整大小
        MMPStage.setTitle("More Messages Ver2.0.0");//设置标题
        MMPStage.getIcons().add(new Image("file:src/main/resources/images/MMP-ICON.jpg"));
        MMPStage.show();//显示
    }

    public static void StartSend(String messages, int times, int spacing, boolean wordmode, int sendmode, int software) {

    }
}
