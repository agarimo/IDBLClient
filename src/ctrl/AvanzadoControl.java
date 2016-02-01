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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.Query;
import main.Var;
import model.ModeloAvanzado;
import model.Modo;
import model.Tipo;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class AvanzadoControl implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML VAR">
    @FXML
    private TableView tabla;

    @FXML
    private TableColumn codigoCL;

    @FXML
    private TableColumn addDataCL;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private ProgressIndicator pgProgreso;

    @FXML
    private Label lbInfoProgreso;

    @FXML
    private Label lbLimite;
//</editor-fold>

    String busqueda;
    Tipo tipo;
    Modo modo;

    Thread thread;

    ObservableList<ModeloAvanzado> items;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbLimite.setText("Límite de la QUERY establecido en " + Var.queryLimit + " registros.");
        pgProgreso.setVisible(false);
        lbInfoProgreso.setVisible(false);
        btAceptar.setDisable(true);
        initializeTable();
    }

    private void initializeTable() {
        codigoCL.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        codigoCL.setCellFactory(new Callback<TableColumn<ModeloAvanzado, String>, TableCell<ModeloAvanzado, String>>() {
            @Override
            public TableCell<ModeloAvanzado, String> call(TableColumn<ModeloAvanzado, String> arg0) {
                return new TableCell<ModeloAvanzado, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setAlignment(Pos.CENTER);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            text = new Text(item);
                            setGraphic(text);
                        }
                    }
                };
            }
        });
        addDataCL.setCellValueFactory(new PropertyValueFactory<>("addData"));
        addDataCL.setCellFactory(new Callback<TableColumn<ModeloAvanzado, String>, TableCell<ModeloAvanzado, String>>() {
            @Override
            public TableCell<ModeloAvanzado, String> call(TableColumn<ModeloAvanzado, String> arg0) {
                return new TableCell<ModeloAvanzado, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setAlignment(Pos.CENTER);
                        setWrapText(true);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            text = new Text(item);
                            text.setWrappingWidth(addDataCL.getWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
            }
        });

        items = FXCollections.observableArrayList();
        tabla.setItems(items);
    }

    public void initializeSetVar(String busqueda, Tipo tipo, Modo modo) {
        this.busqueda = busqueda;
        this.tipo = tipo;
        this.modo = modo;

        switch (tipo) {
            case NIF:
                codigoCL.setText("NIF");
                addDataCL.setVisible(true);
                addDataCL.setText("NOMBRE");
                break;
            case MATRICULA:
                codigoCL.setText("MATRICULA");
                addDataCL.setVisible(false);
                addDataCL.setText("");
                break;
            case EXPEDIENTE:
                codigoCL.setText("EXPEDIENTE");
                addDataCL.setVisible(true);
                addDataCL.setText("CODIGO");
                break;
            default:
            //
        }
    }

    public void runSearch() {
        thread = new Thread(() -> {

            Var.isRunning = true;

            Platform.runLater(() -> {
                pgProgreso.setVisible(true);
                lbInfoProgreso.setVisible(true);
                btAceptar.setDisable(true);
            });

            tableLoad(Query.listaModeloAvanzado(Query.searchSpecificQueryAvanced(busqueda, tipo, modo)));

            Platform.runLater(() -> {
                pgProgreso.setVisible(false);
                lbInfoProgreso.setVisible(false);
                btAceptar.setDisable(false);

                if (items.isEmpty()) {
                    Nav.mainController.setContent(null);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMACIÓN");
                    alert.setHeaderText("ENTRADA SIN REGISTROS.");
                    alert.setContentText("No existe ningún registro para : " + busqueda);
                    alert.showAndWait();
                }
            });

            Var.isRunning = false;

        });
        thread.start();
    }

    public void tableLoad(List<ModeloAvanzado> list) {
        items.clear();
        items.addAll(list);
    }

    @FXML
    void btAceptar(ActionEvent event) {
        ModeloAvanzado aux = (ModeloAvanzado) tabla.getSelectionModel().getSelectedItem();

        if (aux != null) {
            Nav.mainController.setContent(Nav.mainController.loadMulta());
            Nav.multaController.tableLoad(Query.listaModeloMulta(Query.searchQueryAvanced(aux.getId(), tipo)));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE SELECCIÓN");
            alert.setContentText("Debes seleccionar un registro.");

            alert.showAndWait();
        }
    }

    @FXML
    void btCancelar(ActionEvent event) {
        if (Var.isRunning) {
            try {
                Query.bd.stmt.cancel();
                Query.bd.close();
                thread.stop();
            } catch (Exception ex) {
                Logger.getLogger(AvanzadoControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Nav.mainController.setContent(null);
    }

}
