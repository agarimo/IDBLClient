package ctrl;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

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
    public static final String REPORT = "/view/Report.fxml";

    private static MainControl mainController;
    private static MultaControl multaController;
    private static DetalleControl detalleController;
    private static SearchControl searchController;

    public static void setMainController(MainControl controller) {
        Nav.mainController = controller;
    }

    public static void setMultaController(MultaControl controller) {
        Nav.multaController = controller;
    }

    public static void setDetalleController(DetalleControl controller) {
        Nav.detalleController = controller;
    }

    public static void setSearchController(SearchControl controller) {
        Nav.searchController=controller;
    }

    /**
     * Loads the vista specified by the fxml file into the vistaHolder pane of
     * the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached. The fxml
     * is loaded anew and a new vista node hierarchy generated every time this
     * method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example: cache FXMLLoaders cache
     * loaded vista nodes, so they can be recalled or reused allow a user to
     * specify vista node reuse or new creation allow back and forward history
     * like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
            mainController.setVista(FXMLLoader.load(Nav.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void actionDetalle() {
        mainController.botonDetalle();
    }
    
    public static void detalleSetMulta(int id){
        detalleController.setMulta(id);
    }
}
