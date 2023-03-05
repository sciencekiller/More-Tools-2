package com.mt.more_tools;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;

import static com.mt.more_tools.Main.*;

public class MCPWindow {
    public static void Morse_Code() {
        WriteLog("Start morse code window", "INFO", "mcp");
        //定义Stage
        Stage MCPStage = new Stage();

        //定义GridPane
        FlowPane pane = new FlowPane();
        pane.setHgap(15);
        pane.setVgap(15);

        //定义Button
        Button Add_Dot_Button = new Button(".");
        Add_Dot_Button.setPrefWidth(150);
        Button Add_Line_Button = new Button("-");
        Add_Line_Button.setPrefWidth(150);
        Button Delete_Button = new Button("删除");
        Delete_Button.setPrefWidth(150);
        Button Conv_Button = new Button("翻译");
        Conv_Button.setPrefWidth(150);

        //定义标签
        Label Before_Word_Label = new Label("原文:");
        Label After_Word_Label = new Label("译文:");

        //定义文本框
        TextField Before_Word_Input = new TextField();
        Before_Word_Input.setEditable(false);
        Before_Word_Input.setPrefWidth(450);
        TextField After_Word_Output = new TextField();
        After_Word_Output.setPrefWidth(450);

        //定义按钮


        pane.getChildren().addAll(Before_Word_Label, Before_Word_Input, Add_Dot_Button, Add_Line_Button, Delete_Button, Conv_Button, After_Word_Label, After_Word_Output);

        //配置场景
        int img;
        Random random = new Random();
        img = random.nextInt(11);
        img++;
        Image i = new Image(TempFolder + "/MT/Temp/background-" + img + ".jpg");
        WriteLog("Get background image: background-" + img + ".jpg", "INFO", "mcp");
        BackgroundImage bgi = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(bgi);
        pane.setBackground(bg);
        WriteLog("Create morse code scene", "INFO", "mcp");
        Scene MCPScene = new Scene(pane, 600, 400);
        WriteLog("Get morse code stylesheet", "INFO", "mcp");
        URL url_css = Main.class.getClassLoader().getResource("Style.css");
        assert url_css != null;
        MCPScene.getStylesheets().add(url_css.toExternalForm());
        MCPStage.setScene(MCPScene);//设置场景
        MCPStage.setResizable(false);//设置不可调整大小
        MCPStage.setTitle("Morse Code");//设置标题
        WriteLog("Get morse code icon", "INFO", "mcp");
        MCPStage.getIcons().add(new Image(TempFolder + "/MT/Temp/MCP-ICON.jpg"));
        WriteLog("Show morse code window", "INFO", "mcp");
        MCPStage.show();
    }
}
