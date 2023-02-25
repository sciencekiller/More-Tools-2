module com.mmp.more_tools {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires com.alibaba.fastjson2;
    requires jintellitype;
    requires org.apache.commons.lang3;

    opens com.mt.more_tools to javafx.fxml;
    exports com.mt.more_tools;
}