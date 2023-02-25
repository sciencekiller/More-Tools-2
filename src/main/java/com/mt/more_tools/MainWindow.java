//TODO Main窗口
package com.mt.more_tools;

//TODO 导入

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import static com.mt.more_tools.Main.*;


public class MainWindow extends Application {
    //TODO 重写start方法，生成窗口
    @Override
    public void start(Stage primaryStage) {
        WriteLog("Start Main window", "INFO");
        //TODO 定义GridPane
        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setVgap(15);

        //TODO 定义标签
        Label WelcomeLabel = new Label("欢迎来到More Tools");
        WelcomeLabel.setFont(Font.font("KaiTi", 20));

        Label SelectPartLabel = new Label("请选择组件:");

        //TODO 定义选择组件下拉框
        ComboBox<String> SelectPartCombobox = new ComboBox<>();//定义
        SelectPartCombobox.getItems().addAll("More Messages");//定义子成员
        SelectPartCombobox.setPlaceholder(new Label("这里空空如也..."));//为空时显示
        SelectPartCombobox.setEditable(false);//设置不可编辑
        SelectPartCombobox.setValue("More Messages");//设置默认值
        SelectPartCombobox.setPrefWidth(300);//设置宽度500

        //TODO 定义按钮
        Button StartButton = new Button("打开");//打开按钮
        StartButton.setPrefWidth(100);//设置宽度100
        Button ExitButton = new Button("退出");//退出按钮
        ExitButton.setPrefWidth(100);//设置宽度100
        Button AboutButton = new Button("关于");//关于按钮
        AboutButton.setPrefWidth(100);//设置宽度100
        Button WebsiteButton = new Button("官网");//官网按钮
        WebsiteButton.setPrefWidth(100);//设置宽度100

        //TODO 添加组件
        pane.add(WelcomeLabel, 0, 0);
        pane.add(SelectPartLabel, 0, 1);
        pane.add(SelectPartCombobox, 1, 1);
        pane.add(StartButton, 1, 2);
        pane.add(AboutButton, 1, 3);
        pane.add(WebsiteButton, 1, 4);
        pane.add(ExitButton, 1, 5);

        //TODO 创建打开按钮行动
        StartButton.setOnAction(actionEvent -> {
            String CurrentSelect = SelectPartCombobox.getValue();
            switch (CurrentSelect) {
                case "More Messages" -> RunMoreMessages();
            }
        });

        //TODO 创建官网按钮行动
        WebsiteButton.setOnAction(actionEvent -> {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://sciencekill.netlify.app"));
            } catch (Exception e) {
                Alerts.ErrorAlert();
                WriteLog(e, "Failed to open web browser");
                System.exit(1);
            }
        });

        //TODO 创建退出按钮行动
        ExitButton.setOnAction(actionEvent -> {
            WriteLog("Software exit", "INFO");
            System.exit(0);//退出
        });

        //TODO 创建关于按钮行动
        AboutButton.setOnAction(actionEvent -> {
            Alerts.MainAboutAlert();
            WriteLog("Show about page", "INFO");
        });

        //TODO 创建场景
        WriteLog("Creat scene", "INFO");
        Scene primaryScene = new Scene(pane, 600, 400);//定义场景
        WriteLog("Get Stylesheet", "INFO");
        URL url_css = MainWindow.class.getClassLoader().getResource("Style.css");
        assert url_css != null;
        primaryScene.getStylesheets().add(url_css.toExternalForm());
        primaryStage.setScene(primaryScene);//设置场景
        primaryStage.setResizable(false);//设置不可调整大小
        primaryStage.setTitle("More Tools Ver2.0.0");//设置标题
        WriteLog("Get icon", "INFO");
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/MT-ICON.jpg"));//设置图标
        WriteLog("Show main window", "INFO");
        primaryStage.show();//显示
    }

    //TODO 构建启动More Messages方法
    public void RunMoreMessages() {
        WriteLog("Start More-Messages", "INFO");
        MMPWindow.More_Messages();
    }
}
