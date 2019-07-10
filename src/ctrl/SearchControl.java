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
package ctrl;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.Var;
import model.ModeloMulta;
import model.Modo;
import model.Op;
import model.OpType;
import model.Tipo;
import tools.CalculaNif;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class SearchControl implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private VBox panelBusqueda;
    @FXML
    private VBox panelTipo;
    @FXML
    private VBox panelModo;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btBuscar;
    @FXML
    private RadioButton rbNif;
    @FXML
    private RadioButton rbMatricula;
    @FXML
    private RadioButton rbExpediente;
    @FXML
    private RadioButton rbNormal;
    @FXML
    private RadioButton rbComienza;
    @FXML
    private RadioButton rbContiene;
//</editor-fold>
    Op op;
    Tipo tipo;
    Modo modo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GlyphsDude.setIcon(btBuscar, MaterialIcon.SEARCH, "22");
        adminMode(Var.modoAdmin);
        tipo = Tipo.NIF;
        modo = Modo.NORMAL;
    }

    @FXML
    void rbComienza(MouseEvent event) {
        this.modo = Modo.COMIENZA;
    }

    @FXML
    void rbContiene(MouseEvent event) {
        this.modo = Modo.CONTIENE;
    }

    @FXML
    void rbExpediente(MouseEvent event) {
        this.tipo = Tipo.EXPEDIENTE;
    }

    @FXML
    void rbMatricula(MouseEvent event) {
        this.tipo = Tipo.MATRICULA;
    }

    @FXML
    void rbNif(MouseEvent event) {
        this.tipo = Tipo.NIF;
    }

    @FXML
    void rbNormal(MouseEvent event) {
        this.modo = Modo.NORMAL;
    }

    @FXML
    void search(ActionEvent event) {

        String busqueda = searchGetBusqueda();
        tfBuscar.setText(busqueda);
        String query = Query.searchQuery(busqueda, tipo, modo);

        if (modo == Modo.NORMAL) {
            List<ModeloMulta> list = Query.listaModeloMulta(query);

            if (list.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMACIÓN");
                alert.setHeaderText("ENTRADA SIN REGISTROS.");
                alert.setContentText("No existe ningún registro para : " + busqueda);
                alert.showAndWait();
            } else {
                Nav.mainController.botonBuscar(new ActionEvent());
                tfBuscar.setText("");
                Nav.mainController.setContent(Nav.mainController.loadMulta());
                Nav.multaController.tableLoad(list, busqueda);

                op = new Op();
                op.setOpType(OpType.QUERY);
                op.setOpDetail(query.replace("'", "\\'"));
                op.run();
            }
        } else {
            Nav.mainController.botonBuscar(new ActionEvent());
            tfBuscar.setText("");
            Nav.mainController.setContent(Nav.mainController.loadAvanzado());
            Nav.avanzadoController.initializeSetVar(busqueda, tipo, modo);
            Nav.avanzadoController.runSearch();
        }
    }

    private String searchGetBusqueda() {
        String aux = tfBuscar.getText().toUpperCase().trim();

        if (tipo == Tipo.NIF && modo == Modo.NORMAL) {
            try {
                CalculaNif cn = new CalculaNif();

                if (cn.letrasCif.contains("" + aux.charAt(0))) {
                    if (aux.length() == 8) {
                        aux = cn.calcular(aux);
                    }
                } else if (cn.letrasNie.contains("" + aux.charAt(0))) {
                    if (aux.length() <= 8) {
                        aux = cn.calcular(aux);
                    }
                } else if (aux.length() <= 8) {
                    aux = cn.calcular(aux);
                }
                return aux;
            } catch (Exception ex) {
                return aux;
            }
        } else {
            return aux;
        }
    }

    public void adminMode(boolean bol) {
        panelModo.setVisible(bol);
        modo = Modo.NORMAL;
        rbNormal.setSelected(true);
    }
}
