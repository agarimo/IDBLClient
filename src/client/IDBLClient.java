package client;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Agarimo
 */
public class IDBLClient extends Application {

    public static Stage stage;
    public static HostServicesDelegate hostServices;

    @Override
    public void start(Stage stage) throws Exception {
        Variables.iniciaVariables();
        hostServices= HostServicesDelegate.getInstance(this);
        IDBLClient.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
