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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static main.Var.configFile;
import static main.Var.defaultFile;
import util.Files;
import util.Ftp;

/**
 *
 * @author Agarimo
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        if (!isDefault()) {
            getDefault();
        }
        Var.initVar();
        Var.hostServices = HostServicesDelegate.getInstance(this);
        
        Var.stage = stage;
        Var.stage.setResizable(false);

        Var.stage.setOnCloseRequest(event -> {
            System.out.println("Cerrando app");
            Var.xit();
        });

        Var.stage.setScene(createScene(loadMainPane()));
        Var.stage.show();
    }
    
    private void test(){
        try {
            Ftp ftp = new Ftp(Var.conFtp);
            ftp.createDir("aaaa");
            ftp.downloadFile("/2016-02-05/BOE-N-2016-64020.pdf", new File("test.pdf"));
            ftp.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        scene.getStylesheets().setAll(getClass().getResource("/resources/modena.css").toExternalForm());

        return scene;
    }

    private boolean isDefault() {
        File file = new File(configFile);
        return file.exists();
    }

    private void getDefault() {
        File file = new File(configFile);
        InputStream inputStream = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            inputStream = getClass().getResourceAsStream(defaultFile);
            br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            file.createNewFile();
            Files.escribeArchivo(file, sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
