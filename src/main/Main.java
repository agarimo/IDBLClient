/*
 * The MIT License
 *
 * Copyright 2016 Agarimo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package main;

import com.sun.javafx.application.HostServicesDelegate;
import ctrl.MainControl;
import ctrl.Nav;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Agarimo
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Var.iniciaVariables();
        Var.hostServices = HostServicesDelegate.getInstance(this);
        Var.stage = stage;
        Var.stage.setMinHeight(600);
        Var.stage.setMinWidth(800);

        Var.stage.setOnCloseRequest(event -> {
            Var.clearFiles();
        });
        
        Var.stage.setScene(createScene(loadMainPane()));
        Var.stage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(Nav.MAIN));
        MainControl mainController = loader.getController();
        Nav.setMainController(mainController);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
//        scene.getStylesheets().setAll(getClass().getResource("/css/vista.css").toExternalForm());

        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
