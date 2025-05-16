module org.example.chatbot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;

    opens org.example.chatbot to javafx.fxml;
    exports org.example.chatbot;
    exports com.chatbot;
    opens com.chatbot to javafx.fxml;
    exports com.chatbot.controller;
    opens com.chatbot.controller to javafx.fxml;
    exports com.chatbot.service;
    opens com.chatbot.service to javafx.fxml;
    exports com.chatbot.model;
    opens com.chatbot.model to javafx.fxml;

}