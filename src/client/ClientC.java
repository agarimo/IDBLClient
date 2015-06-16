package client;

import static client.IDBLClient.stage;
import entidades.MultaS;
import entidades.VistaAvanzado;
import entidades.VistaMulta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import model.ModeloMulta;
import model.ModeloSancionado;
import util.CalculaNif;
import util.Dates;

public class ClientC {

    //<editor-fold defaultstate="collapsed" desc="VARIABLES FXML">
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton rbMatricula;

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
    private TableView<ModeloSancionado> listaAvanzado;

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
    private TableView<ModeloMulta> tablaMultas;

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
    private ToggleGroup bgOpciones;

    @FXML
    private Label lbCif;

     @FXML
    private Label lbPoblacion;
     
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
    private MenuItem btCerrar;

    @FXML
    private MenuItem btNuevaB;

    @FXML
    private MenuItem btModoAdmin;

    @FXML
    private Label lbBuscando;

    @FXML
    private ProgressIndicator pgBuscando;

    @FXML
    private TableColumn organismoCL;
    @FXML
    private TableColumn cifCL;
    @FXML
    private TableColumn matriculaCL;
    @FXML
    private TableColumn publicacionCL;

    @FXML
    private TableColumn vencimientoCL;

    @FXML
    private TableColumn expedienteCL;

    @FXML
    private TableColumn faseCL;

    @FXML
    private TableColumn nifCL;

    @FXML
    private TableColumn nombreCL;

    @FXML
    private Accordion acordeon;

    @FXML
    private Label lbNombreV;

    @FXML
    private Label lbEurosV;

    @FXML
    private Label lbPuntosV;

    @FXML
    private Label lbArticuloV;

    @FXML
    private Label lbOrganismoV;
    
    @FXML
    private TextField lbLineaV;

    @FXML
    private Pane panelAviso;

//    @FXML
//    private MenuItem cmCif;
//
//    @FXML
//    private MenuItem cmNombre;
//
//    @FXML
//    private MenuItem cmMatricula;
//</editor-fold>
    ObservableList<ModeloMulta> multas;
    ObservableList<ModeloSancionado> multasLista;

    private double x, y;
    private static int typ = 1, opt = 1, avg = 1;
    private int selectedMulta = -1;
    private String selectedLista = null;
    private String link;
    private List listadoMultas = new ArrayList();
    public List listadoAvg = new ArrayList();
    CalculaNif cal = new CalculaNif();

    //<editor-fold defaultstate="collapsed" desc="CONROL BUSQUEDA">
    String getBusqueda() {
        String aux = tfBuscar.getText().toUpperCase().trim();

        if (rbDesactivado.isSelected() && typ == 1) {
            try {
                if (Variables.cadenaCif.contains("" + aux.charAt(0))) {
                    if (aux.length() == 8) {
                        aux = cal.calcular(aux);
                    }
                } else if (Variables.cadenaNie.contains("" + aux.charAt(0))) {
                    if (aux.length() == 8) {
                        aux = cal.calcular(aux);
                    }
                } else {
                    if (aux.length() <= 8) {
                        aux = cal.calcular(aux);
                    }
                }
            } catch (Exception e) {
                //
            }
        }

        return aux;
    }

    @FXML
    void buscar(ActionEvent event) {

        if (rbDesactivado.isSelected()) {
            verPVista();
            cargarMultas(getBusqueda());
        } else {
            verPAvanzado();
            cargarAvanzado(getBusqueda());
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="GENERAL">
    @FXML
    void setTipoBusqueda(ActionEvent event) {
        if (rbNif.isSelected()) {
            typ = 1;
            tfBuscar.setText("");
            tfBuscar.setPromptText("Inserte NIF...");
        }

        if (rbMatricula.isSelected()) {
            typ = 2;
            tfBuscar.setText("");
            tfBuscar.setPromptText("Inserte MATRICULA...");
        }

        if (rbExpediente.isSelected()) {
            typ = 3;
            tfBuscar.setText("");
            tfBuscar.setPromptText("Inserte EXPEDIENTE...");
        }
    }

    @FXML
    void setOpcionBusqueda(ActionEvent event) {
        if (Variables.modoAdmin) {
            opt = 2;
        } else {
            opt = 1;
        }
    }

    @FXML
    void setAvanzado(ActionEvent event) {

        if (rbDesactivado.isSelected()) {
            panelAviso.setVisible(false);
            avg = 1;
        }

        if (rbComienza.isSelected()) {
            panelAviso.setVisible(false);
            avg = 2;
        }

        if (rbContiene.isSelected()) {
            panelAviso.setVisible(true);
            avg = 3;
        }

        if (rbTermina.isSelected()) {
            panelAviso.setVisible(true);
            avg = 4;
        }
    }

    void setPorDefecto() {
        this.rbUltimo.setSelected(true);
        opt = 1;
        this.rbDesactivado.setSelected(true);
        avg = 1;
        this.rbTodas.setSelected(true);
    }

    @FXML
    void setMostrar(ActionEvent event) {
        if (rbTodas.isSelected()) {
            cargarDatosTabla(listadoMultas);
        } else {
            cargarDatosTabla(getInPlazo());
        }
    }

    @FXML
    void cerrarApp(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cerrar la aplicación?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void verPInicio(ActionEvent event) {
        panelVista.setVisible(false);
        panelMulta.setVisible(false);
        panelAvanzado.setVisible(false);
        panelInicio.setVisible(true);
    }

    void verPInicio() {
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

    private static boolean cerrarApp() {
        return false;
    }

    @FXML
    void clear() {
        setPorDefecto();
        verPInicio();
        tfBuscar.setText("");
        lbLocalizadas.setText("");
        lbEnPlazo.setText("");
    }

    @FXML
    void minimizar() {
        IDBLClient.stage.setIconified(true);
    }

    @FXML
    void modoAdmin(ActionEvent event) {
        if (Variables.modoAdmin) {
            Variables.modoAdmin = false;
            activaAdmin();
        } else {
            login();
            activaAdmin();
        }
    }

    void activaAdmin() {
        if (Variables.modoAdmin) {
            pBusquedaAvanzada.setVisible(true);
            btModoAdmin.setText("Modo Normal");
            opt = 2;
        } else {
            pBusquedaAvanzada.setVisible(false);
            btModoAdmin.setText("Modo Admin");
            opt = 1;
        }
    }

    void login() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Acceso");
        dialog.setHeaderText("Introduzca la contraseña");

        // Set the icon (must be included in the project).
        //        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 100, 10, 10));

        //        TextField username = new TextField();
        //        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        //        grid.add(new Label("Username:"), 0, 0);
        //        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 0);
        grid.add(password, 1, 0);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>("", password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals(Variables.passwordAdmin)) {
                Variables.modoAdmin = true;
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ACCESO DENEGADO");
                alert.setContentText("Contraseña incorrecta");
                alert.showAndWait();
            }
            //            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONTROL DETALLE MULTA">
    @FXML
    void printMulta(ActionEvent event) {
        //do nothing
    }

    @FXML
    void verWeb(ActionEvent event) {
        System.out.println(link);
        IDBLClient.hostServices.showDocument(link);
    }

    @FXML
    void verMulta(ActionEvent event) {
        if (selectedMulta < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("¡Error!");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar una multa para continuar");

            alert.showAndWait();
        } else {
            cargarDatosMulta(SqlIDBL.cargaMultaS(selectedMulta));
            verPMulta();
        }
    }

    private void cargarDatosMulta(MultaS aux) {
        this.lbOrganismo.setText(aux.getBol().getOrigenS());
        this.lbFechaP.setText(Dates.imprimeFecha(aux.getBol().getFechaPublicacion()));
        this.lbFechaV.setText(Dates.imprimeFecha(aux.getFechaVencimiento()));
        this.lbBoe.setText(aux.getBol().getnBoe());
        this.lbNombre.setText(aux.getSanc().getNombre());
        this.lbCif.setText(aux.getSan().getNif());
        this.lbPoblacion.setText(aux.getSanc().getLocalidad());
        this.lbMatricula.setText(aux.getVeh().getMatricula());
        this.lbExpediente.setText(aux.getSanc().getExpediente());
        this.lbFase.setText(aux.getFase());
        this.lbArticulo.setText(aux.getSanc().getArticulo());
        this.lbFecha.setText(Dates.imprimeFecha(aux.getSanc().getFechaMulta()));
        this.lbImporte.setText(aux.getSanc().getCuantia());
        this.lbPuntos.setText(aux.getSanc().getPuntos());
        this.lbLinea.setText(aux.getSanc().getLinea());
        setLinkWeb(aux.getSanc().getLink());
    }

    private void setLinkWeb(String link) {
        this.link = link;

        if (link.contains("sede.dgt.gob.es")) {
            
            if(link.contains("https: ")){
                link=link.replace("https: ", "https://");
            }
            btVerWeb.setDisable(false);
            btVerWeb.setText("Ver en la web");
            this.link = link.replace("sede.dgt.gob.es", "sedeapl.dgt.gob.es");
        } else {
            btVerWeb.setDisable(true);
            btVerWeb.setText("Visualización en web no disponible");
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONTROL VISTA MULTAS">

    private void inicializarTabla() {
        organismoCL.setCellValueFactory(new PropertyValueFactory<>("organismo"));
        cifCL.setCellValueFactory(new PropertyValueFactory<>("cif"));
        matriculaCL.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        expedienteCL.setCellValueFactory(new PropertyValueFactory<>("expediente"));
        faseCL.setCellValueFactory(new PropertyValueFactory<>("fase"));
        publicacionCL.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        vencimientoCL.setCellValueFactory(new PropertyValueFactory<>("fechaV"));

        multas = FXCollections.observableArrayList();
        tablaMultas.setItems(multas);
    }

    private void cargarDatosTabla(List lista) {
        multas.clear();
        VistaMulta aux;
        ModeloMulta model;
        Iterator it = lista.iterator();

        while (it.hasNext()) {
            aux = (VistaMulta) it.next();
            model = new ModeloMulta();
            model.id = aux.getId();
            model.organismo.set(aux.getOrganismo());
            model.cif.set(aux.getCif());
            model.matricula.set(aux.getMatricula());
            model.expediente.set(aux.getExpediente());
            model.fase.set(aux.getFase());
            model.fecha.set(Dates.imprimeFecha(aux.getPublicacion()));
            model.fechaV.set(Dates.imprimeFecha(aux.getVencimiento()));

            multas.add(model);
        }

        lbLocalizadas.setText(Integer.toString(listadoMultas.size()));
        lbEnPlazo.setText(Integer.toString(getInPlazo().size()));
    }

    List getInPlazo() {
        List list = new ArrayList();
        Iterator it = listadoMultas.iterator();
        VistaMulta aux;

        while (it.hasNext()) {
            aux = (VistaMulta) it.next();

            if (aux.isPlazo()) {
                list.add(aux);
            }
        }
        return list;
    }

    private void cargarMultas(String aux) {
        listadoMultas = SqlIDBL.listaMultas(VistaMulta.SQLBuscar(aux, typ, opt));
        cargarDatosTabla(listadoMultas);

        if (listadoMultas.isEmpty()) {
            listadoMultas = SqlIDBL.listaMultas(VistaMulta.SQLBuscar(tfBuscar.getText().toUpperCase().trim(), typ, opt));
            cargarDatosTabla(listadoMultas);

            if (listadoMultas.isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("No se han encontrado registros.");

                alert.showAndWait();
            }

        }
        tfBuscar.setText("");
    }

    private void mostrarDatosMulta(MultaS aux) {
        this.lbNombreV.setText(aux.getSanc().getNombre());
        this.lbEurosV.setText(aux.getSanc().getCuantia());
        this.lbPuntosV.setText(aux.getSanc().getPuntos());
        this.lbArticuloV.setText(aux.getSanc().getArticulo());
        this.lbOrganismoV.setText(aux.getBol().getOrigenS());
        this.lbLineaV.setText(aux.getSanc().getLinea());
    }

    private void limpiarDatosMulta() {
        this.lbNombreV.setText("");
        this.lbEurosV.setText("");
        this.lbPuntosV.setText("");
        this.lbArticuloV.setText("");
        this.lbOrganismoV.setText("");
        this.lbLineaV.setText("");
    }

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     *
     * @return
     */
    private ModeloMulta getMulta() {

        if (tablaMultas != null) {
            List<ModeloMulta> tabla = tablaMultas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                ModeloMulta a = tabla.get(0);
                return a;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void getSelectedMulta() {
        final ModeloMulta persona = getMulta();
        selectedMulta = multas.indexOf(persona);

        if (persona != null) {
            selectedMulta = persona.id;
        } else {
            selectedMulta = -1;
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONTROL VISTA AVANZADA">

//    private void inicializarLista() {
//        multasLista = FXCollections.observableArrayList();
//        listaAvanzado.setItems(multasLista);
//    }
//
//    private void cargarDatosLista(List lista) {
//        multasLista.clear();
//        String aux;
//        Iterator it = lista.iterator();
//
//        while (it.hasNext()) {
//            aux = (String) it.next();
//            multasLista.add(aux);
//        }
//    }
    private void inicializarLista() {

        nifCL.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nombreCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        switch (typ) {
            case 1:
                nifCL.setText("NIF");
                nombreCL.setText("NOMBRE");
                nombreCL.setVisible(true);
                break;

            case 2:
                nifCL.setText("MATRICULA");
                nombreCL.setText("---");
                nombreCL.setVisible(false);
                break;

            case 3:
                nifCL.setText("EXPEDIENTE");
                nombreCL.setText("NOMBRE");
                nombreCL.setVisible(true);
                break;
        }

        multasLista = FXCollections.observableArrayList();
        listaAvanzado.setItems(multasLista);
    }

    private void cargarDatosLista(List lista) {
        inicializarLista();
        multasLista.clear();
        VistaAvanzado aux;
        ModeloSancionado model;
        Iterator it = lista.iterator();

        while (it.hasNext()) {
            aux = (VistaAvanzado) it.next();
            model = new ModeloSancionado();
            model.id = aux.getId();
            model.nif.set(aux.getNif());
            model.nombre.set(aux.getNombre());

            multasLista.add(model);
        }

        lbLocalizadas.setText(Integer.toString(listadoMultas.size()));
        lbEnPlazo.setText(Integer.toString(getInPlazo().size()));
    }

    @FXML
    void continuarAvanzado(ActionEvent event) {
        if (selectedLista != null) {
            verPVista();
            cargarMultas(selectedLista);
            setPorDefecto();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("¡Error!");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar un elemento para continuar");

            alert.showAndWait();
        }
    }

    public void cargarAvanzado(String aux) {
        pgBuscando.setVisible(true);
        lbBuscando.setVisible(true);

        listadoAvg = SqlIDBL.listaMultasA(VistaAvanzado.SQLBuscarA(aux, typ, avg), Variables.tipoBusqueda[typ], typ);
        cargarDatosLista(listadoAvg);

        pgBuscando.setVisible(false);
        lbBuscando.setVisible(false);
    }

//    private String getMultaLista() {
//        if (listaAvanzado != null) {
//            String aux = listaAvanzado.getSelectionModel().getSelectedItem();
//            return aux;
//        }
//        return null;
//    }
    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     *
     * @return
     */
    private ModeloSancionado getMultaLista() {

        if (listaAvanzado != null) {
            List<ModeloSancionado> tabla = listaAvanzado.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                ModeloSancionado a = tabla.get(0);
                return a;
            }
        }
        return null;
    }

//    private void getSelectedMultaLista() {
//        String aux = getMultaLista();
//        selectedLista = multasLista.indexOf(aux);
//    }
    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void getSelectedMultaLista() {
        String aux = getMultaLista().getNif();
        selectedLista = aux;
    }
//</editor-fold>

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

        if (Variables.modoAdmin) {
            rbTodo.setVisible(true);
        } else {
            rbTodo.setVisible(false);
        }

        inicializarTabla();

        final ObservableList<ModeloMulta> aux = tablaMultas.getSelectionModel().getSelectedItems();
        aux.addListener(selectorTabla);

        final ObservableList<ModeloSancionado> aux1 = listaAvanzado.getSelectionModel().getSelectedItems();
        aux1.addListener(selectorLista);

    }

    //<editor-fold defaultstate="collapsed" desc="LISTENERS">
    /**
     * Listener de la tabla multas
     */
    private final ListChangeListener<ModeloMulta> selectorTabla
            = (ListChangeListener.Change<? extends ModeloMulta> c) -> {
                getSelectedMulta();
                if (selectedMulta > 0) {
                    mostrarDatosMulta(SqlIDBL.cargaMultaS(selectedMulta));
                } else {
                    limpiarDatosMulta();
                }
            };

    /**
     * Listener de la lista multasAvg
     */
    private final ListChangeListener<ModeloSancionado> selectorLista
            = (ListChangeListener.Change<? extends ModeloSancionado> c) -> {
                getSelectedMultaLista();
            };
//</editor-fold>
}
