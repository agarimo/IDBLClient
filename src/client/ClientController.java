package client;

import static client.IDBLClient.stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

public class ClientController {

    private double x, y;
    private int typ = 1, opt = 1, avg = 1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton rbMatricula;

    @FXML
    private Button btSalir;

    @FXML
    private RadioButton rbTodo;

    @FXML
    private Button btBuscar;

    @FXML
    private Label lbPuntos;

    @FXML
    private Label lbExpediente;

    @FXML
    private AnchorPane panelBusqueda;

    @FXML
    private ToggleGroup bgTipo;

    @FXML
    private ListView<?> listaAvanzado;

    @FXML
    private Label lbLocalizadas;

    @FXML
    private AnchorPane panelInicio;

    @FXML
    private Label lbImporte;

    @FXML
    private Button btVolverMulta;

    @FXML
    private RadioButton rbUltimo;

    @FXML
    private RadioButton rbContiene;

    @FXML
    private TableView<?> tablaMultas;

    @FXML
    private Button btVerWeb;

    @FXML
    private RadioButton rbTermina;

    @FXML
    private Label lbLinea;

    @FXML
    private Label lbFase;

    @FXML
    private Label lbEnPlazo;

    @FXML
    private AnchorPane panelVista;

    @FXML
    private Label lbArticulo;

    @FXML
    private Menu mHerramientas;

    @FXML
    private ToggleGroup bgMostrar;

    @FXML
    private Label lbFecha;

    @FXML
    private RadioButton rbComienza;

    @FXML
    private TitledPane pTipoBusqueda;

    @FXML
    private TitledPane pOpcionesBusqueda;

    @FXML
    private Button btPrint;

    @FXML
    private RadioButton rbNif;

    @FXML
    private StackPane paneles;

    @FXML
    private RadioButton rbDesactivado;

    @FXML
    private ToggleGroup bgAvanzada;

    @FXML
    private Label lbNombre;

    @FXML
    private Button btVerMulta;

    @FXML
    private Label lbFechaP;

    @FXML
    private VBox win;

    @FXML
    private Label lbFechaV;

    @FXML
    private AnchorPane panelAvanzado;

    @FXML
    private Label lbOrganismo;

    @FXML
    private RadioButton rbEnPlazo;

    @FXML
    private AnchorPane panelMulta;

    @FXML
    private Button btVolverAvanzado;

    @FXML
    private RadioButton rbExpediente;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Button btCancelarMulta;

    @FXML
    private ToggleGroup bgOpciones;

    @FXML
    private Label lbCif;

    @FXML
    private TitledPane pBusquedaAvanzada;

    @FXML
    private Label lbMatricula;

    @FXML
    private Button btContinuarAvanzado;

    @FXML
    private Label lbBoe;

    @FXML
    private RadioButton rbTodas;

    @FXML
    void buscar(ActionEvent event) {

        if (rbDesactivado.isSelected()) {
            verPVista();
        } else {
            verPAvanzado();
        }
    }

    @FXML
    void setTipoBusqueda(ActionEvent event) {
        if (rbNif.isSelected()) {
            typ = 1;
        }

        if (rbMatricula.isSelected()) {
            typ = 2;
        }

        if (rbExpediente.isSelected()) {
            typ = 3;
        }
    }

    @FXML
    void setOpcionBusqueda(ActionEvent event) {
        if (rbUltimo.isSelected()) {
            opt = 1;
        }

        if (rbTodo.isSelected()) {
            opt = 2;
        }
    }

    @FXML
    void setAvanzado(ActionEvent event) {

        if (rbDesactivado.isSelected()) {
            avg = 1;
        }

        if (rbComienza.isSelected()) {
            avg = 2;
        }

        if (rbContiene.isSelected()) {
            avg = 3;
        }

        if (rbTermina.isSelected()) {
            avg = 4;
        }
    }

    @FXML
    void setMostrar(ActionEvent event) {
        
    }

    @FXML
    void cerrarApp(ActionEvent event) {
        Action response = Dialogs.create()
                .owner(stage)
                .title("Seleccione una opción")
                .message("¿Desea cerrar la aplicación?")
                .showConfirm();

        if (response == Dialog.ACTION_YES) {
            System.exit(0);
        }
    }

    @FXML
    void setListaAvanzado(ActionEvent event) {
        
    }

    @FXML
    void continuarAvanzado(ActionEvent event) {
        verPVista();
    }

    @FXML
    void verPInicio(ActionEvent event) {
        panelVista.setVisible(false);
        panelMulta.setVisible(false);
        panelAvanzado.setVisible(false);
        panelInicio.setVisible(true);
    }

    @FXML
    void verPVista(ActionEvent event) {
        panelVista.setVisible(true);
        panelMulta.setVisible(false);
        panelAvanzado.setVisible(false);
        panelInicio.setVisible(false);
    }
    
    void verPVista() {
        panelVista.setVisible(true);
        panelMulta.setVisible(false);
        panelAvanzado.setVisible(false);
        panelInicio.setVisible(false);
    }

    void verPAvanzado() {
        panelVista.setVisible(false);
        panelMulta.setVisible(false);
        panelAvanzado.setVisible(true);
        panelInicio.setVisible(false);
    }

    void verPMulta() {
        panelVista.setVisible(false);
        panelMulta.setVisible(true);
        panelAvanzado.setVisible(false);
        panelInicio.setVisible(false);
    }

    @FXML
    void printMulta(ActionEvent event) {

    }

    @FXML
    void verWeb(ActionEvent event) {

    }

    @FXML
    void setSelectedMulta(ActionEvent event) {

    }

    @FXML
    void verMulta(ActionEvent event) {
        verPMulta();
    }

    @FXML
    void cogerVentana(MouseEvent event) {
        x = stage.getX() - event.getScreenX();
        y = stage.getY() - event.getScreenY();
    }

    @FXML
    void soltarVentana(MouseEvent event) {
        stage.setX(event.getScreenX() + x);
        stage.setY(event.getScreenY() + y);
    }

    @FXML
    void moverVentana(MouseEvent event) {
        stage.setX(event.getScreenX() + x);
        stage.setY(event.getScreenY() + y);
    }

    @FXML
    void initialize() {
        IDBLClient.stage.initStyle(StageStyle.TRANSPARENT);
        IDBLClient.stage.setResizable(false);
        IDBLClient.stage.setTitle("IDBL");
        IDBLClient.stage.setOnCloseRequest((WindowEvent ev) -> {
            if (!cerrarApp()) {
                ev.consume();
            }
        });
    }

    private static boolean cerrarApp() {
        return false;
    }
}
