package org.rentcar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
public class RentCarApp extends Application {

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webView.getEngine().load(getClass().getResource("/web/rentcar.html").toExternalForm());

        Scene scene = new Scene(webView, 1150, 700);
        stage.setTitle("RentCar - Sistema de gestión");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
