package ctrl;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import model.ModeloMulta;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate simple access from
 * anywhere in the application.
 */
public class Nav {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN = "/view/Main.fxml";
    public static final String MULTA = "/view/Multa.fxml";
    public static final String DETALLE = "/view/Detalle.fxml";
    public static final String CONFIG = "/view/Config.fxml";
    public static final String SEARCH = "/view/Search.fxml";
    public static final String AVANZADO = "/view/Avanzado.fxml";
    public static final String REPORT = "/view/Report.fxml";
    public static final String TEST = "/view/Test.fxml";

    public static MainControl mainController;
    public static MultaControl multaController;
    public static DetalleControl detalleController;
    public static SearchControl searchController;
    public static AvanzadoControl avanzadoController;
    public static TestControl testController;

    public static void setAvanzadoController(AvanzadoControl controller) {
        Nav.avanzadoController = controller;
    }

    public static void setDetalleController(DetalleControl controller) {
        Nav.detalleController = controller;
    }

    public static void setMainController(MainControl controller) {
        Nav.mainController = controller;
    }

    public static void setMultaController(MultaControl controller) {
        Nav.multaController = controller;
    }

    public static void setSearchController(SearchControl controller) {
        Nav.searchController = controller;
    }

    public static void setTestController(TestControl controller) {
        Nav.testController = controller;
    }
}
