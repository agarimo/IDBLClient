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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.ModeloMulta;
import util.Dates;

/**
 * FXML Controller class
 *
 * @author Agarimo
 */
public class MultaControl implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML Var">
    @FXML
    private TableView tabla;

    @FXML
    private TableColumn organismoCL;

    @FXML
    private TableColumn cifCL;

    @FXML
    private TableColumn matriculaCL;

    @FXML
    private TableColumn expedienteCL;

    @FXML
    private TableColumn faseCL;

    @FXML
    private TableColumn publicacionCL;

    @FXML
    private TableColumn vencimientoCL;

    @FXML
    private Label lbLocalizadas;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbPuntos;

    @FXML
    private Label lbCuantia;

    @FXML
    private TextField tfLinea;

    @FXML
    private Button btDetalle;

//</editor-fold>
    ObservableList<ModeloMulta> multas;
    ObservableValue<String> cargadas;
    //<editor-fold defaultstate="collapsed" desc="LISTENER">
    /**
     * Listener de la tabla multas
     */
    private final ListChangeListener<ModeloMulta> selectorTabla = (ListChangeListener.Change<? extends ModeloMulta> c) -> {
        tableShowSelected(tableGetSelected());
    };
//</editor-fold>

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
    }

    private void initializeTable() {
        organismoCL.setCellValueFactory(new PropertyValueFactory<>("organismo"));
        organismoCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setAlignment(Pos.CENTER_LEFT);
                        setWrapText(true);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            text = new Text(item);
                            text.setWrappingWidth(organismoCL.getWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
            }
        });
        cifCL.setCellValueFactory(new PropertyValueFactory<>("cif"));
        cifCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
        matriculaCL.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        matriculaCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
        expedienteCL.setCellValueFactory(new PropertyValueFactory<>("expediente"));
        expedienteCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
        faseCL.setCellValueFactory(new PropertyValueFactory<>("fase"));
        faseCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
        publicacionCL.setCellValueFactory(new PropertyValueFactory<>("fecha_publicacion"));
        publicacionCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
        vencimientoCL.setCellValueFactory(new PropertyValueFactory<>("fecha_vencimiento"));
        vencimientoCL.setCellFactory(new Callback<TableColumn<ModeloMulta, String>, TableCell<ModeloMulta, String>>() {
            @Override
            public TableCell<ModeloMulta, String> call(TableColumn<ModeloMulta, String> arg0) {
                return new TableCell<ModeloMulta, String>() {
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
                            if (initializeTableCalculaPlazo(item) >= 0) {
                                text.setFill(Color.GREEN);
                            } else {
                                text.setFill(Color.RED);
                            }
                            setGraphic(text);
                        }
                    }
                };
            }
        });

        multas = FXCollections.observableArrayList();
        tabla.setItems(multas);

        final ObservableList<ModeloMulta> aux = tabla.getSelectionModel().getSelectedItems();
        aux.addListener(selectorTabla);

    }

    private int initializeTableCalculaPlazo(String date) {
        Date curdate = Dates.curdate();
        Date fecha = Dates.formatFecha(date, "yyyy-MM-dd");

        return fecha.compareTo(curdate);
    }

    public void tableLoad(String busqueda, List<ModeloMulta> aux) {
        multas.clear();
        tableShowSelected(null);
        multas.addAll(aux);
        lbLocalizadas.setText(Integer.toString(multas.size()));
    }

    private ModeloMulta tableGetSelected() {
        try {
            ModeloMulta aux = (ModeloMulta) tabla.getSelectionModel().getSelectedItem();
            return aux;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private void tableShowSelected(ModeloMulta aux) {
        if (aux != null) {
            lbNombre.setText(aux.getNombre());
            lbPuntos.setText(aux.getPuntos());
            lbCuantia.setText(aux.getCuantia());
            tfLinea.setText(aux.getLinea());
        } else {
            lbNombre.setText("");
            lbPuntos.setText("");
            lbCuantia.setText("");
            tfLinea.setText("");
        }
    }

    @FXML
    void verDetalle(ActionEvent event) {
        ModeloMulta aux = tableGetSelected();

        if (aux != null) {
            Nav.detalleController.setMulta(aux.getId());
            Nav.mainController.botonDetalle();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE SELECCIÓN");
            alert.setContentText("Debes seleccionar una sanción.");
            alert.showAndWait();
        }

    }
}
