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

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import main.Var;
import static main.Var.popup;
import util.Conexion;
import util.Sql;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class ConfigControl implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private Label lbEstadoAdmin;

    @FXML
    private ToggleButton btOn;

    @FXML
    private ToggleButton btOff;

    @FXML
    private TextField tfHost;

    @FXML
    private TextField tfPort;

    @FXML
    private Button btTestCon;

    @FXML
    private Button btAceptar;

    @FXML
    private TextField tfQuery;

    @FXML
    private PasswordField pfPass;

    @FXML
    private Button btCancelar;

    @FXML
    private VBox mainPane;

    @FXML
    private VBox maskPane;

    @FXML
    private Pane passPane;

//</editor-fold>
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Var.modoAdmin) {
            btOn.setSelected(true);
            lbEstadoAdmin.setText("Activo");
            lbEstadoAdmin.setTextFill(Color.GREEN);
        } else {
            btOff.setSelected(true);
            lbEstadoAdmin.setText("Inactivo");
            lbEstadoAdmin.setTextFill(Color.RED);
        }

        tfHost.setPromptText(Var.con.getDireccion());
        tfPort.setPromptText(Var.con.getPuerto());
        tfQuery.setText(Var.queryLimit);

        btCancelar.requestFocus();
    }

    @FXML
    void testCon(ActionEvent event) {
        Sql bd;
        String host = tfHost.getText();
        String port = tfPort.getText();

        Conexion test = new Conexion();
        test.setDireccion(host);
        test.setPuerto(port);
        test.setUsuario(Var.con.getUsuario());
        test.setPass(Var.con.getPass());

        try {
            bd = new Sql(test);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Conectado correctamente");
            alert.setContentText("¿Desea guardar los cambios?");

            alert.initOwner(Var.popup);
            alert.initModality(Modality.WINDOW_MODAL);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                tfHost.setText("");
                tfHost.setPromptText(host);
                tfPort.setText("");
                tfPort.setPromptText(port);
                Var.con.setDireccion(host);
                Var.con.setPuerto(port);
            } else {
                tfHost.setText("");
                tfPort.setText("");
            }

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se puede conectar.");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    void validatePass(ActionEvent event) {
        String aux = pfPass.getText();

        if (aux.equals(Var.passwordAdmin)) {
            passPane.setVisible(false);
            maskPane.setVisible(false);
            mainPane.setEffect(null);
        }
    }

    @FXML
    void onAction(MouseEvent event) {
        Var.modoAdmin = true;
        lbEstadoAdmin.setText("Activo");
        lbEstadoAdmin.setTextFill(Color.GREEN);
        Nav.adminMode(true);
    }

    @FXML
    void offAction(MouseEvent event) {
        Var.modoAdmin = false;
        lbEstadoAdmin.setText("Inactivo");
        lbEstadoAdmin.setTextFill(Color.RED);
        Nav.adminMode(false);
    }

    @FXML
    void cerrar(ActionEvent event) {
        try {
            String aux = tfQuery.getText();

            if (aux != null) {
                int a = Integer.parseInt(aux);
                Var.queryLimit = aux;
            }
        } catch (Exception ex) {
            //
        }

        popup.close();
    }

}
