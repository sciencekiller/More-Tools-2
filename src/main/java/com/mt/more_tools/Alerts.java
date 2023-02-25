package com.mt.more_tools;

import javafx.scene.control.Alert;

public class Alerts {
    public static void MainAboutAlert() {
        Alert AboutAlert = new Alert(Alert.AlertType.INFORMATION);
        AboutAlert.setTitle("关于");
        AboutAlert.setHeaderText("More Tools Version" + Main.MainVersion);
        AboutAlert.setContentText("感谢您使用More Tools Version" + Main.MainVersion + "\n本软件由版权属于程添宇(Sciencekill)\nCopyright 2023 Sciencekill");
        AboutAlert.showAndWait();
    }

    public static void MMPAboutAlert() {
        Alert AboutAlert = new Alert(Alert.AlertType.INFORMATION);
        AboutAlert.setTitle("关于");
        AboutAlert.setHeaderText("More Messages Version" + Main.MMPVersion);
        AboutAlert.setContentText("感谢您使用More Messages Version" + Main.MMPVersion + "\n本软件由版权属于程添宇(Sciencekill)\n!本软件为More Tools中的一个组件!\nCopyright 2023 Sciencekill");
        AboutAlert.showAndWait();
    }

    public static void NoNumberInputAlert() {
        Alert NoNumberInputAlert = new Alert(Alert.AlertType.ERROR);
        NoNumberInputAlert.setTitle("未输入次数");
        NoNumberInputAlert.setHeaderText("您未输入次数!");
        NoNumberInputAlert.setContentText("请检查是否输入了次数，如果是误报错，请截图反馈");
        NoNumberInputAlert.showAndWait();
    }

    public static void NoSpacingInputAlert() {
        Alert NoSpacingInputAlert = new Alert(Alert.AlertType.ERROR);
        NoSpacingInputAlert.setTitle("未输入间隔");
        NoSpacingInputAlert.setHeaderText("您未输入间隔!");
        NoSpacingInputAlert.setContentText("请检查是否输入了间隔，如果是误报错，请截图反馈");
        NoSpacingInputAlert.showAndWait();
    }

    public static void NoMessageInputAlert() {
        Alert NoMessageInputAlert = new Alert(Alert.AlertType.ERROR);
        NoMessageInputAlert.setTitle("未输入文本");
        NoMessageInputAlert.setHeaderText("您未输入文本!");
        NoMessageInputAlert.setContentText("请检查是否输入了文本，如果是误报错，请截图反馈");
        NoMessageInputAlert.showAndWait();
    }

    public static void SendInterrupted() {
        Alert SendInterrupted = new Alert(Alert.AlertType.ERROR);
        SendInterrupted.setTitle("终止");
        SendInterrupted.setHeaderText("用户强制终止了发送");
        SendInterrupted.setContentText("已将发送强制停止!");
        SendInterrupted.showAndWait();
    }


    public static void SendOver() {
        Alert SendOver = new Alert(Alert.AlertType.INFORMATION);
        SendOver.setTitle("完成!");
        SendOver.setHeaderText("发送完成!");
        SendOver.setContentText("已发送完成!");
        SendOver.showAndWait();
    }

    public static void InputErrorAlert() {
        Alert InputErrorAlert = new Alert(Alert.AlertType.ERROR);
        InputErrorAlert.setTitle("输入错误");
        InputErrorAlert.setHeaderText("您的输入有误");
        InputErrorAlert.setContentText("请仔细检查输入，如果是误报错，请截图反馈");
        InputErrorAlert.showAndWait();
    }

    public static void ErrorAlert() {
        Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
        ;
        ErrorAlert.setTitle("错误");
        ErrorAlert.setHeaderText("软件遇到错误，请查看日志");
        ErrorAlert.setHeaderText("日志文件位于缓存地址/MT/Logs");
        ErrorAlert.showAndWait();
    }
}
