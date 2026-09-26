package org.rentcar;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import netscape.javascript.JSObject;

import org.rentcar.clases.GestorRentCar;
import org.rentcar.controlador.Controlador;
import org.rentcar.servicios.IGestorRentCar;
import org.rentcar.servicios.IValidadorNumeroPerfecto;
import org.rentcar.servicios.ValidadorNumeroPerfecto;
import org.rentcar.web.WebBridge;

public class RentCarApp extends Application {

    private WebBridge webBridge;


    @Override
    public void start(Stage stage) {

        WebView webView =
                new WebView();

        WebEngine webEngine =
                webView.getEngine();

        IGestorRentCar gestorRentCar =
                new GestorRentCar();

        IValidadorNumeroPerfecto validador =
                new ValidadorNumeroPerfecto();

        Controlador controlador =
                new Controlador(
                        gestorRentCar,
                        validador
                );

        webBridge =
                new WebBridge(
                        controlador
                );


        webEngine.getLoadWorker()
                .stateProperty()
                .addListener(
                        (observable,
                         estadoAnterior,
                         estadoNuevo) -> {

                            if (estadoNuevo
                                    == Worker.State.SUCCEEDED) {

                                JSObject window =
                                        (JSObject)
                                                webEngine.executeScript(
                                                        "window"
                                                );

                                window.setMember(
                                        "javaBridge",
                                        webBridge
                                );

                                System.out.println(
                                        "Bridge RentCar conectado."
                                );

                                webEngine.executeScript(
                                        "if (window.inicializarAplicacion) "
                                                + "window.inicializarAplicacion();"
                                );
                            }
                        });


        var recurso =
                getClass().getResource(
                        "/web/rentcar.html"
                );

        if (recurso == null) {
            throw new IllegalStateException(
                    "No se encontró /web/rentcar.html"
            );
        }

        webEngine.load(
                recurso.toExternalForm()
        );


        Scene scene =
                new Scene(
                        webView,
                        1150,
                        700
                );

        stage.setTitle(
                "RentCar - Sistema de gestión"
        );

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}