package client;

import static client.IDBLClient.stage;
import entidades.MultaS;
import entidades.VistaMulta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.ModeloMulta;
import util.CalculaNif;
import util.Dates;

public class ClientController {

    //<editor-fold defaultstate="collapsed" desc="VARIABLES FXML">
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
    private ListView<String> listaAvanzado;

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
    private Label lbBuscando;

    @FXML
    private ProgressIndicator pgBuscando;

    @FXML
    private TableColumn nombreCL;
    @FXML
    private TableColumn cifCL;
    @FXML
    private TableColumn matriculaCL;
    @FXML
    private TableColumn publicacionCL;

    @FXML
    private TableColumn vencimientoCL;

    @FXML
    private Accordion acordeon;

    @FXML
    private Button btMinimizar;

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
    ObservableList<String> multasLista;

    private double x, y;
    private int typ = 1, opt = 1, avg = 1;
    private int selectedMulta = -1;
    private int selectedLista = -1;
    private String link;
    private List listadoMultas = new ArrayList();
    private List listadoAvg = new ArrayList();

    //<editor-fold defaultstate="collapsed" desc="CONROL BUSQUEDA">
    String getBusqueda() {
        String aux = tfBuscar.getText().toUpperCase().trim();

        if (rbDesactivado.isSelected() && typ == 1) {
            aux = validaNif(aux);
        }

        tfBuscar.setText(aux);
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
            pgBuscando.setVisible(false);
            lbBuscando.setVisible(false);

        }
    }

    private String validaNif(String trim) {
        CalculaNif cn = new CalculaNif();

        if (trim.length() == 9) {
            return trim;
        } else {
            return cn.calcular(trim);
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
        this.lbMatricula.setText(aux.getVeh().getMatricula());
        this.lbExpediente.setText(aux.getSanc().getExpediente());
        this.lbFase.setText(aux.getFase());
        this.lbArticulo.setText(aux.getSanc().getArticulo());
        this.lbFecha.setText(Dates.imprimeFecha(aux.getSanc().getFechaMulta()));
        this.lbImporte.setText(aux.getSanc().getCuantia());
        this.lbPuntos.setText(aux.getSanc().getPuntos());
        this.lbLinea.setText(aux.getSanc().getLinea());
        this.link = aux.getSanc().getLink();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONTROL VISTA MULTAS">

    private void inicializarTabla() {
        nombreCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cifCL.setCellValueFactory(new PropertyValueFactory<>("cif"));
        matriculaCL.setCellValueFactory(new PropertyValueFactory<>("matricula"));
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
            model.nombre.set(aux.getNombre());
            model.cif.set(aux.getCif());
            model.matricula.set(aux.getMatricula());
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
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado registros.");

            alert.showAndWait();
        }
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

    private void inicializarLista() {
        multasLista = FXCollections.observableArrayList();
        listaAvanzado.setItems(multasLista);
    }

    private void cargarDatosLista(List lista) {
        multasLista.clear();
        String aux;
        Iterator it = lista.iterator();

        while (it.hasNext()) {
            aux = (String) it.next();
            multasLista.add(aux);
        }
    }

    @FXML
    void continuarAvanzado(ActionEvent event) {
        if (selectedLista != -1) {
            verPVista();
            tfBuscar.setText(multasLista.get(selectedLista));
            cargarMultas(multasLista.get(selectedLista));
            setPorDefecto();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("¡Error!");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar un elemento para continuar");

            alert.showAndWait();
        }
    }

    private void cargarAvanzado(String aux) {
        listadoAvg = SqlIDBL.listaMultasA(VistaMulta.SQLBuscarA(aux, typ, avg), Variables.tipoBusqueda[typ]);
        cargarDatosLista(listadoAvg);
    }

    private String getMultaLista() {
        if (listaAvanzado != null) {
            String aux = listaAvanzado.getSelectionModel().getSelectedItem();
            return aux;
        }
        return null;
    }

    private void getSelectedMultaLista() {
        String aux = getMultaLista();
        selectedLista = multasLista.indexOf(aux);
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
        inicializarLista();

        final ObservableList<ModeloMulta> aux = tablaMultas.getSelectionModel().getSelectedItems();
        aux.addListener(selectorTabla);

        final ObservableList<String> aux1 = listaAvanzado.getSelectionModel().getSelectedItems();
        aux1.addListener(selectorLista);

    }

    //<editor-fold defaultstate="collapsed" desc="LISTENERS">
    /**
     * Listener de la tabla multas
     */
    private final ListChangeListener<ModeloMulta> selectorTabla
            = (ListChangeListener.Change<? extends ModeloMulta> c) -> {
                getSelectedMulta();
            };

    /**
     * Listener de la lista multasAvg
     */
    private final ListChangeListener<String> selectorLista
            = (ListChangeListener.Change<? extends String> c) -> {
                getSelectedMultaLista();
            };
//</editor-fold>
}
